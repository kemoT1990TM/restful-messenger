package com.tkartas.resources;

import com.tkartas.model.Comment;
import com.tkartas.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class CommentResource {

    // == fields ==
    CommentService commentService=new CommentService();

    // == GET requests ==
    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId){
        return commentService.getAllComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId){
        return commentService.getComment(messageId,commentId);
    }

    // == POST requests ==
    @POST
    public Comment addComment(@PathParam("messageId") long messageId,Comment comment){
        return commentService.addComment(messageId,comment);
    }

    // == PUT requests ==
    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId,Comment comment){
        comment.setId(commentId);
        return commentService.updateComment(messageId,comment);
    }

    // == DELETE requests ==
    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId){
        commentService.removeComment(messageId,commentId);
    }
}
