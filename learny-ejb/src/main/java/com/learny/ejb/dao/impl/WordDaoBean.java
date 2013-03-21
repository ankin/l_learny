package com.learny.ejb.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.learny.ejb.dao.core.AbstractDao;
import com.learny.ejb.dao.local.WordDaoLocal;
import com.learny.persistence.entity.Word;

@Stateless
public class WordDaoBean extends AbstractDao<Word> implements WordDaoLocal {

    @Override
    public Word findByOriginal(String original) {
        Query query = getEntityManager().createNamedQuery(Word.QUERY_BY_ORIGINAL);
        query.setParameter(Word.PARAM_ORIGINAL, original);
        try {
            return (Word) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
