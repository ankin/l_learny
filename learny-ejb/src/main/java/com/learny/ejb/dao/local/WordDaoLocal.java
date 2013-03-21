package com.learny.ejb.dao.local;

import javax.ejb.Local;

import com.learny.ejb.dao.core.Dao;
import com.learny.persistence.entity.Word;

@Local
public interface WordDaoLocal extends Dao<Word> {

    Word findByOriginal(String original);

}
