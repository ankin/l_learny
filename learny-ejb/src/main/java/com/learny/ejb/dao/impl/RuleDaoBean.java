package com.learny.ejb.dao.impl;

import javax.ejb.Stateless;

import com.learny.ejb.dao.core.AbstractDao;
import com.learny.ejb.dao.local.RuleDaoLocal;
import com.learny.persistence.entity.Rule;

@Stateless
public class RuleDaoBean extends AbstractDao<Rule> implements RuleDaoLocal {

}
