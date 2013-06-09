package com.learny.ejb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.learny.dto.RecordHistory;
import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.ejb.dao.local.DeWordDaoLocal;
import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.ejb.service.local.RecordLocal;
import com.learny.ejb.service.local.TranslatorLocal;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.vocabulary.DeWord;

@Stateless
@Named("recordBean")
public class RecordBean implements RecordLocal {

    @Inject
    private RecordDaoLocal recordDao;

    @Inject
    private DeWordDaoLocal deWordDao;

    @Inject
    private CommentDaoLocal commentDao;

    @Inject
    private UserDaoLocal userDao;

    @Inject
    private TranslatorLocal translatorBean;

    @Override
    public Record saveOrUpdate(Record record) {
        return recordDao.saveOrUpdate(record);
    }

    @Override
    public Record findCurrentRecordByUserEmail(String userEmail) {
        Record record = recordDao.findRecordsByUserEmail(userEmail).get(0);
        translatorBean.translate(record.getWords(), record.getUser().getLanguage());
        return record;
    }

    @Override
    public Record findRecordByUuidFullyInitialized(String uuid) {
        Record record = recordDao.findRecordByUuidFullyInitialized(uuid);
        translatorBean.translate(record.getWords(), record.getUser().getLanguage());
        return record;
    }

    @Override
    public List<RecordHistory> findRecordHistoriesByUserEmail(String userEmail) {
        List<Record> records = recordDao.findRecordsByUserEmail(userEmail);
        List<RecordHistory> recordHistories = new ArrayList<>();
        for (Record record : records) {
            recordHistories.add(new RecordHistory(record.getUuid(), record.getDateCreated()));
        }
        return recordHistories;
    }

    @Override
    public DeWord saveOrUpdateWord(String recordUuid, DeWord deWord) {
        Record record = recordDao.findByUuid(recordUuid);
        DeWord reloadedWord = deWordDao.findByUuid(deWord.getUuid());
        record.removeWord(reloadedWord);
        record.addWord(reloadedWord);
        recordDao.saveOrUpdate(record);
        translatorBean.translate(reloadedWord, record.getUser().getLanguage());
        return reloadedWord;
    }

    @Override
    public void removeWordFromRecord(String recordUuid, String wordUuid) {
        Record record = recordDao.findByUuid(recordUuid);
        DeWord deWord = deWordDao.findByUuid(wordUuid);
        record.removeWord(deWord);
        recordDao.saveOrUpdate(record);
    }

}
