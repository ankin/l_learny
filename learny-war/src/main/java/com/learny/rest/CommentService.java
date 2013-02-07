package com.learny.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.learny.ejb.service.local.CommentLocal;
import com.learny.persistence.entity.Comment;

@Path("/comment")
@RequestScoped
public class CommentService {

    @Inject
    @Named("commentBean")
    private CommentLocal commentBean;

    @GET
    @Path("/getall")
    @Produces("application/json")
    public List<Comment> getCommentsByRecordId(@QueryParam("recordUuid") String recordUuid) {
        System.out.println("recordUuid: " + recordUuid); // TODO: add logging instead
        return commentBean.findCommentsByRecordUuid(recordUuid);
    }

    @POST
    @Path("/new/{objectType}/{objectId}/")
    @Produces("application/json")
    public void newComment(@PathParam("objectType") String objectType, @PathParam("objectId") String objectId,
            @FormParam("commentText") String text) {
        System.out.println("objectType: " + objectType);
        System.out.println("objectId: " + objectId);
        //throw new RuntimeException();
    }

}
