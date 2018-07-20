package com.tkartas.resources;

import com.tkartas.model.Message;
import com.tkartas.resources.beans.MessageFilterBean;
import com.tkartas.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/messages")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class MessageResource {

    // == fields ==
    MessageService messageService=new MessageService();

    // == GET requests ==
    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean,@Context UriInfo uriInfo){
        if(filterBean.getYear()>0){
            return messageService.getAllMessagesForYear(filterBean.getYear(),uriInfo);
        }
        if(filterBean.getStart()>0 && filterBean.getSize()>0){
            return messageService.getAllMessagesPaginated(filterBean.getStart(),filterBean.getSize(),uriInfo);
        }
            return messageService.getAllMessages(uriInfo);
    }

//    @GET
//    public List<Message> getMessages(){
//        return messageService.getAllMessages();
//    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id,@Context UriInfo uriInfo){
        Message message = messageService.getMessage(id,uriInfo);
        return message;
    }

    // == POST requests ==
    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message newMessage=messageService.addMessage(message);
        String newId=String.valueOf(newMessage.getId());
        URI uri =uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                .entity(newMessage)
                .build();
//       return messageService.addMessage(message);
    }

    // == PUT requests ==
    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long id,Message message){
        message.setId(id);
        return messageService.updateMessage(message);
    }

    // == DELETE requests ==
    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long id){
       messageService.removeMessage(id);
    }

    // == Subresources ==
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
        return new CommentResource();
    }

}
