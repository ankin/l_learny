package com.learny.ejb.dao.impl;

import javax.ejb.Stateless;

import com.learny.ejb.dao.core.AbstractDao;
import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.persistence.entity.Comment;

@Stateless
public class CommentDaoBean extends AbstractDao<Comment> implements CommentDaoLocal {

}
