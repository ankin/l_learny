package com.learny.ejb.dao.local;

import javax.ejb.Local;

import com.learny.ejb.dao.core.Dao;
import com.learny.persistence.entity.Rule;

@Local
public interface RuleDaoLocal extends Dao<Rule> {


}
