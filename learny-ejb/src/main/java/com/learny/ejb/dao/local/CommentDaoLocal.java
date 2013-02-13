package com.learny.ejb.dao.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.ejb.dao.core.Dao;
import com.learny.persistence.entity.Comment;
import com.learny.persistence.entity.RecordComment;

@Local
public interface CommentDaoLocal extends Dao<Comment> {

    List<RecordComment> findRecordCommentsByRecordUuid(String recordUuid);

}
