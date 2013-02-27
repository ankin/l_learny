package com.learny.ejb.dao.impl;

import javax.ejb.Stateless;

import com.learny.ejb.dao.core.AbstractDao;
import com.learny.ejb.dao.local.WordDaoLocal;
import com.learny.persistence.entity.Word;

@Stateless
public class WordDaoBean extends AbstractDao<Word> implements WordDaoLocal {

}
