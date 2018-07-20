package com.tkartas.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
public class Message {

    // == fields ==
    private long id;
    private String message;
    private Date created;
    private String author;

    @Getter(AccessLevel.NONE)
    private Map<Long,Comment> comments=new HashMap<>();
    private List<Link> links =new ArrayList<>();

    // == constructors ==
    public Message(long id, String message, String author) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.created = new Date();
    }

    @XmlTransient
    @JsonbTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }

//    public void addLink(String url, String rel){
//        Link link=new Link();
//        link.setLink(url);
//        link.setRel(rel);
//        links.add(link);
//    }
}
