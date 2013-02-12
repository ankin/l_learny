package com.learny.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.learny.ejb.service.local.CommentLocal;
import com.learny.persistence.entity.Comment;

@Path(CommentService.PATH)
@RequestScoped
public class CommentService {

    public final static String PATH = "/comment";

    private final static Logger LOGGER = LogManager.getLogger(CommentService.class);

    @Inject
    private CommentLocal commentBean;

    @GET
    @Path("/getall")
    @Produces("application/json")
    public List<Comment> getCommentsByRecordId(@QueryParam("recordUuid") String recordUuid) {
        LOGGER.info("getCommentsByRecordId() was called with param recordUuid: " + recordUuid);
        return commentBean.findCommentsByRecordUuid(recordUuid);
    }

    @POST
    @Path("/new/{objectType}/{objectId}/")
    @Produces("application/json")
    public void newComment(@PathParam("objectType") String objectType, @PathParam("objectId") String objectId,
            @FormParam("commentText") String text) {
        System.out.println("objectType: " + objectType);
        System.out.println("objectId: " + objectId);
        List<Comment> comments = new ArrayList<>();
        //throw new RuntimeException();
    }

}
