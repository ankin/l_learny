package com.learny.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.learny.ejb.local.LearningLineLocal;
import com.learny.persistence.entity.Record;

@Path("/translate")
@RequestScoped
public class TranslateService {

	@Inject
    private LearningLineLocal translateLocal;

	@GET
	@Path("/name")
	@Produces("application/json")
    public List<Record> getRecords() {
        return translateLocal.getRecords(0);
	}

}
