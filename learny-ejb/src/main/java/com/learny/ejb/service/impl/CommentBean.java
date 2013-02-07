package com.learny.ejb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.service.local.CommentLocal;
import com.learny.persistence.entity.Comment;

@Stateless
@Named("commentBean")
public class CommentBean implements CommentLocal {

    @EJB
    private RecordDaoLocal recordDao;

    @Override
    public List<Comment> findCommentsByRecordUuid(String uuid) {
        //TODO: find better way to init comments
        List<Comment> comments = new ArrayList<Comment>();
        comments.addAll(recordDao.findByUuid(uuid).getComments());
        return comments;
    }

}
