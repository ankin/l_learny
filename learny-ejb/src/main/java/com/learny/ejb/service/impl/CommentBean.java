package com.learny.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.ejb.service.local.CommentLocal;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.RecordComment;
import com.learny.persistence.entity.User;

@Stateless
public class CommentBean implements CommentLocal {

    @Inject
    private RecordDaoLocal recordDao;

    @Inject
    private CommentDaoLocal commentDao;
    
    @Inject
    private UserDaoLocal userDao;

    @Override
    public List<RecordComment> findRecordCommentsByRecordUuid(String recordUuid) {
        return commentDao.findRecordCommentsByRecordUuid(recordUuid);
    }

    @Override
    public void createRecordComment(String userUuid, String recordUuid, String commentText) {
        Record record = recordDao.findByUuid(recordUuid);
        User user = userDao.findByUuid(userUuid);
        RecordComment comment = new RecordComment();
        comment.setUser(user);
        comment.setText(commentText);
        comment.setRecord(record);
        commentDao.saveOrUpdate(comment);

    }

}
