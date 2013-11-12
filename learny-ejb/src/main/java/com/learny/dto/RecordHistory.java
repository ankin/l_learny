package com.learny.dto;

import java.io.Serializable;
import java.util.Date;

import com.learny.persistence.entity.Record;

public class RecordHistory implements Serializable {

    private static final long serialVersionUID = -967476431197778061L;

    private final String recordUuid;

    private final Date dateCreated;

    private final String name;

    public RecordHistory(Record record) {
        super();
        this.recordUuid = record.getUuid();
        this.dateCreated = record.getDateCreated();
        this.name = record.getName();
    }

    public String getRecordUuid() {
        return recordUuid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getName() {
        return name;
    }

}
