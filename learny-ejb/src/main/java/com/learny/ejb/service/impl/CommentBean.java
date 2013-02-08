package com.learny.ejb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.service.local.CommentLocal;
import com.learny.persistence.entity.Comment;

@Stateless
public class CommentBean implements CommentLocal {

    @Inject
    private RecordDaoLocal recordDao;

    @Override
    public List<Comment> findCommentsByRecordUuid(String uuid) {
        //TODO: find better way to init comments
        List<Comment> comments = new ArrayList<>();
        comments.addAll(recordDao.findByUuid(uuid).getComments());
        return comments;
    }

}
