package com.learny.ejb.service.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.persistence.entity.Comment;

@Local
public interface CommentLocal {

    List<Comment> findCommentsByRecordUuid(String uuid);

}
