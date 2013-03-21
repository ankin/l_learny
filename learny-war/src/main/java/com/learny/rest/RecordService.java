package com.learny.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learny.ejb.service.local.CommentLocal;
import com.learny.ejb.service.local.RecordLocal;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.Word;
import com.learny.rest.core.AbstractService;

@Path(RecordService.PATH)
@RequestScoped
public class RecordService extends AbstractService {

    private final static Logger logger = LoggerFactory.getLogger(RecordService.class);

    public final static String PATH = "/record";

    @Inject
    private RecordLocal recordBean;

    @Inject
    private CommentLocal commentBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Record getCurrentRecord() {
        logger.info("getCurrentRecord() method invocked");
        return recordBean.getCurrentRecordByUserEmail(getCurrentUserEmail());
    }

    @PUT
    @Path("{recordUuid}/words/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Word> saveWords(@PathParam("recordUuid") String recordUuid, List<Word> words) {
        logger.info("getWordsByRecord() was called with param recordUuid: " + recordUuid + ", words: " + words);
        return recordBean.saveWords(recordUuid, words);

    }

}
