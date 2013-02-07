package com.learny.ejb.dao.impl;

import javax.ejb.Stateless;

import com.learny.ejb.dao.core.AbstractDao;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.persistence.entity.User;

@Stateless
public class UserDaoBean extends AbstractDao<User> implements UserDaoLocal {

}
