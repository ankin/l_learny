package com.learny.ejb.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.ejb.service.local.RecordLocal;
import com.learny.ejb.service.local.TranslatorLocal;
import com.learny.persistence.entity.Record;

@Stateless
@Named("recordBean")
public class RecordBean implements RecordLocal {

    @Inject
    private RecordDaoLocal recordDao;

    @Inject
    private CommentDaoLocal commentDao;

    @Inject
    private UserDaoLocal userDao;

    @Inject
    private TranslatorLocal translator;

    @Override
    public Record saveOrUpdate(Record record) {
        return recordDao.saveOrUpdate(record);
    }

    @Override
    public Record getCurrentRecordByUserEmail(String userEmail) {
        Record record = recordDao.findRecordsByUserEmail(userEmail).get(0);
        translator.translate(record.getWords());
        return record;
    }

}
