package com.learny.ejb.service.local;

import javax.ejb.Local;

import com.learny.persistence.entity.Record;

@Local
public interface RecordLocal {

    Record getCurrentRecordByUserEmail(String userUuid);

    Record saveOrUpdate(Record record);

}
