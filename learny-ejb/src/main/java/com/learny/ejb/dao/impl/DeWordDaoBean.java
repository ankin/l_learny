package com.learny.ejb.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.learny.ejb.dao.core.AbstractGenderWordDao;
import com.learny.ejb.dao.local.DeWordDaoLocal;
import com.learny.persistence.entity.vocabulary.DeWord;

@Stateless
public class DeWordDaoBean extends AbstractGenderWordDao<DeWord> implements DeWordDaoLocal {

    private static final int MAX_SEARCH_RESULTS = 5;
    private static final String LIKE = "%";

    @Override
    public List<DeWord> search(String value) {
        Query query = getEntityManager().createNamedQuery(DeWord.QUERY_SEARCH_BY_VALUE);
        query.setParameter(DeWord.PARAM_SEARCH_VALUE, LIKE + value + LIKE);
        return query.setMaxResults(MAX_SEARCH_RESULTS).getResultList();

    }
}
