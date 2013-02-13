package com.learny.ejb.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.learny.ejb.dao.core.AbstractDao;
import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.persistence.entity.Comment;
import com.learny.persistence.entity.RecordComment;

@Stateless
public class CommentDaoBean extends AbstractDao<Comment> implements CommentDaoLocal {

    @Override
    public List<RecordComment> findRecordCommentsByRecordUuid(String recordUuid) {
        Query query = getEntityManager().createNamedQuery(RecordComment.QUERY_BY_RECORD_UUID);
        query.setParameter(RecordComment.PARAM_RECORD_ID, recordUuid);
        return (List<RecordComment>) query.getResultList();
    }

}
