package com.learny.persistence.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@DiscriminatorValue(RecordComment.DISCRIMINATOR)
@NamedQueries(@NamedQuery(name = RecordComment.QUERY_BY_RECORD_UUID,
                          query = "select rc from RecordComment rc where rc.record.uuid=:"
                                  + RecordComment.PARAM_RECORD_ID + " order by rc.dateCreated"))
public class RecordComment extends Comment {

    private static final long serialVersionUID = -5626419384054012420L;

    public final static String DISCRIMINATOR = "RECORD";

    public final static String QUERY_BY_RECORD_UUID = "RecordComment.queryByRecordUuid";
    public final static String PARAM_RECORD_ID = "recordUuid";

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = Record.TABLE_NAME + UNDERSCORE + ID, nullable = false)
    private Record record;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

}
