package com.learny.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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
import com.learny.persistence.entity.RecordComment;
import com.learny.rest.core.AbstractService;

@Path(CommentService.PATH)
@RequestScoped
public class CommentService extends AbstractService {

    public final static String PATH = "/comment";

    private final static Logger LOGGER = LogManager.getLogger(CommentService.class);

    @Inject
    private CommentLocal commentBean;

    @GET
    @Path("record/getall")
    @Produces("application/json")
    @RolesAllowed({ "STUDENT", "DOCENT", "MANAGER" })
    public List<RecordComment> getCommentsByRecordId(@QueryParam("recordUuid") String recordUuid) {
        LOGGER.info("getCommentsByRecordId() was called with param recordUuid: " + recordUuid);
        return commentBean.findRecordCommentsByRecordUuid(recordUuid);
    }

    @POST
    @Path("/record/new/{recordUuid}/")
    @Produces("application/json")
    @RolesAllowed({ "STUDENT", "DOCENT", "MANAGER" })
    public void newComment(@PathParam("recordUuid") String recordUuid, @FormParam("commentText") String commentText) {
        LOGGER.info("newComment() was called with params recordUuid: " + recordUuid + ", commentText:" + commentText);
        commentBean.createRecordComment(getCurrentUserEmail(), recordUuid, commentText);
    }

}
