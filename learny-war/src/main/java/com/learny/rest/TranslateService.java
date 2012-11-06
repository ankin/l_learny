package com.learny.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.learny.ejb.TranslateBean;
import com.learny.persistence.entity.Word;

@Path("/translate")
@RequestScoped
public class TranslateService {

	@Inject
	private TranslateBean translateBean;

	@GET
	@Path("/name")
	@Produces("application/json")
	public List<Word> translate() {
		return translateBean.translate(null);
	}

}
