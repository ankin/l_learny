package com.learny.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.learny.ejb.TestBean;

@Path("/library")
@RequestScoped
public class TestService {

    @Inject
    private TestBean testBean;

    @GET
    @Path("/name")
    public String getTestName() {
        return testBean.getName();
    }

}
