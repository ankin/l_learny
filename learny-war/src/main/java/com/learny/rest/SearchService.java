package com.learny.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learny.ejb.service.local.SearchLocal;
import com.learny.persistence.entity.Word;
import com.learny.rest.core.AbstractService;

@Path(SearchService.PATH)
@RequestScoped
public class SearchService extends AbstractService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    public static final String PATH = "/search";

    @Inject
    private SearchLocal searchBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Word> search(@QueryParam("query") String searchQuery) {
        logger.info("search() method invocked");
        return searchBean.search(searchQuery, getCurrentUser().getLanguage());
        
    }

}
