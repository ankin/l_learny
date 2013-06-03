package com.learny.ejb.dao.impl;

import javax.ejb.Stateless;

import com.learny.ejb.dao.core.AbstractWordDao;
import com.learny.ejb.dao.local.EnWordDaoLocal;
import com.learny.persistence.entity.vocabulary.EnWord;

@Stateless
public class EnWordDaoBean extends AbstractWordDao<EnWord> implements EnWordDaoLocal {



}
