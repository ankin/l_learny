package com.learny.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.vocabulary.DeWord;

public class RecordHistory implements Serializable {

    private static final long serialVersionUID = -967476431197778061L;

    private final String recordUuid;

    private final Date dateCreated;

    private final String name;

    private final List<String> topWords = new ArrayList<>();

    public RecordHistory(Record record) {
        super();
        this.recordUuid = record.getUuid();
        this.dateCreated = record.getDateCreated();
        this.name = record.getName();
        int i = 0;
        for (DeWord word : record.getWords()) {
            if (i == 3) {
                break;
            }
            this.topWords.add(word.getValue());
            i++;
        }
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

    public List<String> getTopWords() {
        return topWords;
    }
}
