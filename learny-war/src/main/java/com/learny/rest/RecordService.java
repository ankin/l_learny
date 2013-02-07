package com.learny.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.learny.ejb.service.local.RecordLocal;
import com.learny.persistence.entity.Record;

@Path("/record")
@RequestScoped
public class RecordService {

    @Inject
    @Named("mockRecordBean")
    private RecordLocal recordBean;

    @GET
    @Path("/get/")
    @Produces("application/json")
    public Record getCurrentRecord() {
        return recordBean.getCurrentRecordByUserUuid("");
    }

    //    @GET
    //    @Path("/current/{date}")
    //    @Produces("application/json")
    //    public List<Record> getRecords(@PathParam("date") Long dateInMilis) {
    //        System.out.println(new Date(dateInMilis));
    //        throw new RuntimeException();
    //    }


}
