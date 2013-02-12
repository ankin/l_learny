package com.learny.ejb.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.learny.ejb.dao.core.AbstractDao;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.persistence.entity.User;

@Stateless
public class UserDaoBean extends AbstractDao<User> implements UserDaoLocal {

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        Query query = getEntityManager().createNamedQuery(User.QUERY_BY_EMAIL_AND_PASSWORD);
        query.setParameter(User.PARAM_EMAIL, email);
        query.setParameter(User.PARAM_PASSWORD, password);
        return (User) query.getSingleResult();

    }
}
