package com.learny.dto;

import java.io.Serializable;
import java.util.Date;

public class RecordHistory implements Serializable {

    private static final long serialVersionUID = -967476431197778061L;

    public RecordHistory(String recordUuid, Date dateCreated) {
        super();
        this.recordUuid = recordUuid;
        this.dateCreated = dateCreated;
    }

    private String recordUuid;

    private Date dateCreated;

    public String getRecordUuid() {
        return recordUuid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

}
