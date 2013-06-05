package com.learny.ejb.dao.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.ejb.dao.core.GenderWordDao;
import com.learny.persistence.entity.vocabulary.DeWord;

@Local
public interface DeWordDaoLocal extends GenderWordDao<DeWord> {

    List<DeWord> search(String value);

}
