package com.learny.ejb.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.service.local.RecordLocal;
import com.learny.persistence.entity.Record;

@Stateless
@Named("recordBean")
public class RecordBean implements RecordLocal {

    @EJB
    private RecordDaoLocal recordDao;


    @Override
    public Record saveOrUpdate(Record record) {
        return recordDao.saveOrUpdate(record);
    }


    @Override
    public Record getCurrentRecordByUserUuid(String userUuid) {
        return recordDao.getRecordsByUserUuid(userUuid).get(0);
    }
}
