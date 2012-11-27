package com.learny.rest;

import java.util.Date;
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

import com.learny.ejb.local.LearningLineLocal;
import com.learny.persistence.entity.Comment;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.User;

@Path("/records")
@RequestScoped
public class RecordsService {

    @Inject
    @Named("mockLearningLineBean")
    private LearningLineLocal learningLineLocal;

    @GET
    @Path("/current/")
    @Produces("application/json")
    public List<Record> getRecords() {

        return learningLineLocal.getRecords(0);
    }

    @GET
    @Path("/current/{date}")
    @Produces("application/json")
    public List<Record> getRecords(@PathParam("date") Long dateInMilis) {
        System.out.println(new Date(dateInMilis));
        throw new RuntimeException();
    }

    @POST
    @Path("/newcomment/")
    public Comment postComment(@FormParam("newComment") String text) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setDateCreated(new Date());
        User user = new User();
        user.setFirstName("Andrii");
        user.setLastName("Kinash");
        comment.setUser(user);
        return comment;
    }

}
