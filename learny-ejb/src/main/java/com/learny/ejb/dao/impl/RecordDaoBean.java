package com.learny.ejb.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.learny.ejb.dao.core.AbstractDao;
import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.persistence.entity.Record;

@Stateless
public class RecordDaoBean extends AbstractDao<Record> implements RecordDaoLocal {

    @Override
    public List<Record> findRecordsByUserUuid(String userUuid) {
        Query query = getEntityManager().createNamedQuery(Record.QUERY_BY_USER_UUID);
        query.setParameter(Record.PARAM_USER_ID, userUuid);
        return query.getResultList();
    }

}
