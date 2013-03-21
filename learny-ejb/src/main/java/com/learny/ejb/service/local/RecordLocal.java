package com.learny.ejb.service.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.Word;

@Local
public interface RecordLocal {

    Record getCurrentRecordByUserEmail(String userUuid);

    Record saveOrUpdate(Record record);

    List<Word> saveWords(String recordUuid, List<Word> words);

}
