package com.tkartas.service;

import com.tkartas.database.DatabaseClass;
import com.tkartas.exception.DataNotFoundException;
import com.tkartas.model.Link;
import com.tkartas.model.Message;
import com.tkartas.resources.MessageResource;
import com.tkartas.resources.ProfileResource;

import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MessageService {

    // == fields ==
    private Map<Long,Message> messages=DatabaseClass.getMessages();

    // == constructors ==

    // == public methods ==
    public List<Message> getAllMessages(UriInfo uriInfo){
        for(Message message:messages.values()){
            message.setLinks(getLinkList(uriInfo,message));
        }
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year,UriInfo uriInfo) {
        List<Message> messageForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                message.setLinks(getLinkList(uriInfo,message));
                messageForYear.add(message);
            }
        }
        return messageForYear;
    }

    public List<Message> getAllMessagesPaginated(int start,int size,UriInfo uriInfo) {
        for(Message message:messages.values()){
            message.setLinks(getLinkList(uriInfo,message));
        }
       ArrayList<Message> list=new ArrayList<>(messages.values());
       if(start+size-1>list.size()){
           return new ArrayList<>();
       }
       return list.subList(start-1,start+size-1);
    }

    public Message getMessage(long id,UriInfo uriInfo){
        Message message= messages.get(id);
        message.setLinks(getLinkList(uriInfo,message));
        if(message==null){
            throw new DataNotFoundException("Message with id "+id+" not found");
        }
        return message;
    }

    public Message addMessage(Message message){
        message.setId(messages.size()+1);
        messages.put(message.getId(),message);
        return message;
    }

    public Message updateMessage(Message message){
        if(message.getId()<=0){
            return null;
        }
        messages.put(message.getId(),message);
        return message;
    }

    public Message removeMessage(long id){
        return messages.remove(id);
    }

    public List<Link> getLinkList(UriInfo uriInfo,Message message){
        List<Link> links=new ArrayList<>();
        Link self=new Link(getUriForSelf(uriInfo,message),"self");
        Link comments=new Link(getUriForComments(uriInfo,message),"comments");
        Link profile=new Link(getUriForProfile(uriInfo,message),"profile");
        links.add(self);
        links.add(comments);
        links.add(profile);
        return links;
    }

    public String getUriForProfile(UriInfo uriInfo, Message message){
        String uri= uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build()
                .toString();
        return uri;
    }
    public String getUriForComments(UriInfo uriInfo,Message message){
        String uri= uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(message.getId()))
                .path("/comments")
                .build()
                .toString();
        return uri;
    }
    public String getUriForSelf(UriInfo uriInfo,Message message){
        String uri= uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
        return uri;
    }
}
