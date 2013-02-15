package com.learny.ejb.dao.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.ejb.dao.core.Dao;
import com.learny.persistence.entity.Record;

@Local
public interface RecordDaoLocal extends Dao<Record> {

    List<Record> findRecordsByUserEmail(String userUuid);

}
