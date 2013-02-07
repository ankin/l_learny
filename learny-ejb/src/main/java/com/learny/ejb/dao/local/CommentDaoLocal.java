package com.learny.ejb.dao.local;

import javax.ejb.Local;

import com.learny.ejb.dao.core.Dao;
import com.learny.persistence.entity.Comment;

@Local
public interface CommentDaoLocal extends Dao<Comment> {

}
