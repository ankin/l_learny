package com.learny.ejb.service.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.dto.RecordHistory;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.vocabulary.DeWord;

@Local
public interface RecordLocal {

    Record saveOrUpdate(Record record);

    Record findRecordByUuidFullyInitialized(String uuid);

    Record findCurrentRecordByUserEmail(String userUuid);

    List<RecordHistory> findRecordHistoriesByUserEmail(String userEmail);

    DeWord saveOrUpdateWord(String recordUuid, DeWord word);

    void removeWordFromRecord(String recordUuid, String wordUuid);

}
