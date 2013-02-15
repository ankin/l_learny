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
    public List<Record> findRecordsByUserEmail(String userEmail) {
        Query query = getEntityManager().createNamedQuery(Record.QUERY_BY_USER_EMAIL);
        query.setParameter(Record.PARAM_USER_EMAIL, userEmail);
        return query.getResultList();
    }

}
