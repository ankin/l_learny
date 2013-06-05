package com.learny.ejb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.learny.dto.RecordHistory;
import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.ejb.dao.local.WordDaoLocal;
import com.learny.ejb.service.local.RecordLocal;
import com.learny.ejb.service.local.TranslatorLocal;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.Word;

@Stateless
@Named("recordBean")
public class RecordBean implements RecordLocal {

    @Inject
    private RecordDaoLocal recordDao;

    @Inject
    private WordDaoLocal wordDao;

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
    public List<Word> updateWords(String recordUuid, List<Word> words) {
        Record record = recordDao.findByUuid(recordUuid);
        record.clearWords();
        record.addWords(saveWords(words));
        recordDao.saveOrUpdate(record);
        translatorBean.translate(record.getWords(), record.getUser().getLanguage());
        return record.getWords();
    }

    @Override
    public Word saveOrUpdateWord(String recordUuid, Word word) {
        Record record = recordDao.findByUuid(recordUuid);
        record.removeWord(word);
        Word savedWord = saveWord(word);
        record.addWord(savedWord);
        recordDao.saveOrUpdate(record);
        translatorBean.translate(savedWord, record.getUser().getLanguage());
        return savedWord;
    }

    @Override
    public void removeWordFromRecord(String recordUuid, String wordUuid) {
        Record record = recordDao.findByUuid(recordUuid);
        Word word = wordDao.findByUuid(wordUuid);
        record.removeWord(word);
        recordDao.saveOrUpdate(record);
    }


    @Override
    public List<Word> saveWords(String recordUuid, List<Word> words) {
        Record record = recordDao.findByUuid(recordUuid);
        record.addWords(saveWords(words));
        recordDao.saveOrUpdate(record);
        translatorBean.translate(record.getWords(), record.getUser().getLanguage());
        return record.getWords();
    }

    private List<Word> saveWords(List<Word> words) {
        List<Word> savedWords = new ArrayList<>();
        for (Word wordOrig : words) {
            Word word = saveWord(wordOrig);
            savedWords.add(word);
        }
        return savedWords;
    }

    private Word saveWord(Word wordOrig) {
        Word word = wordDao.findByOriginal(wordOrig.getOriginal());
        if (word == null) {
            word = wordDao.saveOrUpdate(wordOrig);
        }
        return word;
    }

}
