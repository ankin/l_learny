package com.learny.ejb.dao.impl;

import javax.ejb.Stateless;

import com.learny.ejb.dao.core.AbstractGenderWordDao;
import com.learny.ejb.dao.local.DeWordDaoLocal;
import com.learny.persistence.entity.vocabulary.DeWord;

@Stateless
public class DeWordDaoBean extends AbstractGenderWordDao<DeWord> implements DeWordDaoLocal {

}
