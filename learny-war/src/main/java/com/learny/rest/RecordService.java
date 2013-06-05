package com.learny.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learny.dto.RecordHistory;
import com.learny.ejb.service.local.CommentLocal;
import com.learny.ejb.service.local.RecordLocal;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.Word;
import com.learny.rest.core.AbstractService;

@Path(RecordService.PATH)
@RequestScoped
public class RecordService extends AbstractService {

    private static final Logger logger = LoggerFactory.getLogger(RecordService.class);

    public static final String PATH = "/record";

    @Inject
    private RecordLocal recordBean;

    @Inject
    private CommentLocal commentBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Record getCurrentRecord() {
        logger.info("getCurrentRecord() method invocked");
        return recordBean.findCurrentRecordByUserEmail(getCurrentUser().getEmail());
    }

    @GET
    @Path("{recordUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Record findRecordByUuid(@PathParam("recordUuid") String recordUuid) {
        logger.info("findRecordByUuid() method invocked");
        return recordBean.findRecordByUuidFullyInitialized(recordUuid);
    }

    @GET
    @Path("history/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecordHistory> findCurrentRecordHistories() {
        logger.info("findCurrentRecordHistories() method invocked");
        return recordBean.findRecordHistoriesByUserEmail(getCurrentUser().getEmail());
    }

    @PUT
    @Path("{recordUuid}/words/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Word> updateWords(@PathParam("recordUuid") String recordUuid, List<Word> words) {
        logger.info("updateWords() was called with param recordUuid: " + recordUuid + ", words: " + words);
        return recordBean.updateWords(recordUuid, words);

    }

    @POST
    @Path("{recordUuid}/words/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Word> saveWords(@PathParam("recordUuid") String recordUuid, List<Word> words) {
        logger.info("saveWords() was called with param recordUuid: " + recordUuid + ", words: " + words);
        return recordBean.saveWords(recordUuid, words);

    }

    @PUT
    @Path("{recordUuid}/word/{wordUuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Word updateWord(@PathParam("recordUuid") String recordUuid, Word word) {
        logger.info("updateWord() was called with param recordUuid: " + recordUuid + ", word: " + word);
        return recordBean.saveOrUpdateWord(recordUuid, word);

    }

    @POST
    @Path("{recordUuid}/word/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Word saveWord(@PathParam("recordUuid") String recordUuid, Word word) {
        logger.info("saveWord() was called with param recordUuid: " + recordUuid + ", word: " + word);
        return recordBean.saveOrUpdateWord(recordUuid, word);

    }

    @DELETE
    @Path("{recordUuid}/word/{wordUuid}")
    public void removeWordFromRecord(@PathParam("recordUuid") String recordUuid, @PathParam("wordUuid") String wordUuid) {
        logger.info("removeWordFromRecord() was called with param recordUuid: " + recordUuid + ", word uuid: "
                + wordUuid);
        recordBean.removeWordFromRecord(recordUuid, wordUuid);

    }
}
