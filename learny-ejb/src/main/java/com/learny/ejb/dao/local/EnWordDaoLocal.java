package com.learny.ejb.dao.local;

import javax.ejb.Local;

import com.learny.ejb.dao.core.DefaultWordDao;
import com.learny.persistence.entity.vocabulary.EnWord;

@Local
public interface EnWordDaoLocal extends DefaultWordDao<EnWord> {

}
