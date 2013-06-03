package com.learny.ejb.dao.local;

import javax.ejb.Local;

import com.learny.ejb.dao.core.WordDao;
import com.learny.persistence.entity.vocabulary.DeWord;

@Local
public interface DeWordDaoLocal extends WordDao<DeWord> {

}
