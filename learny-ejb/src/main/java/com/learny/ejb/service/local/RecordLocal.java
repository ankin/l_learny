package com.learny.ejb.service.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.dto.RecordHistory;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.Word;

@Local
public interface RecordLocal {

    Record saveOrUpdate(Record record);

    List<Word> saveWords(String recordUuid, List<Word> words);

    List<Word> updateWords(String recordUuid, List<Word> words);

    Record findRecordByUuidFullyInitialized(String uuid);

    Record findCurrentRecordByUserEmail(String userUuid);

    List<RecordHistory> findRecordHistoriesByUserEmail(String userEmail);

    Word saveOrUpdateWord(String recordUuid, Word word);

    void removeWordFromRecord(String recordUuid, String wordUuid);

}
