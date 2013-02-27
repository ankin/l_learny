package com.learny.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.learny.ejb.service.local.CommentLocal;
import com.learny.ejb.service.local.RecordLocal;
import com.learny.persistence.entity.Record;
import com.learny.rest.core.AbstractService;

@Path(RecordService.PATH)
@RequestScoped
public class RecordService extends AbstractService {

    private final static Logger LOGGER = LogManager.getLogger(RecordService.class);

    public final static String PATH = "/record";

    @Inject
    private RecordLocal recordBean;

    @Inject
    private CommentLocal commentBean;

    @GET
    @Path("/get/")
    @Produces("application/json")
    public Record getCurrentRecord() {
        LOGGER.info("getCurrentRecord() method invocked");
        return recordBean.getCurrentRecordByUserEmail(getCurrentUserEmail());
    }

}
