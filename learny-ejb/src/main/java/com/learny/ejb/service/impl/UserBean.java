package com.learny.ejb.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.ejb.service.exception.LoginException;
import com.learny.ejb.service.local.UserBeanLocal;
import com.learny.persistence.entity.User;
import com.learny.util.LearnyProperties;

@Stateless
public class UserBean implements UserBeanLocal {

    private final static Logger LOGGER = LogManager.getLogger(UserBean.class);

    @Inject
    private UserDaoLocal userDao;

    @Override
    public User login(String email, String password) throws LoginException {
        User user = null;
        try {
            if (LearnyProperties.isLoginWithoutCredentialsEnabled()) {
                String tmpEmail = LearnyProperties.getLoginWithoutCredentialsEmail();
                String tmpPassword = LearnyProperties.getLoginWithoutCredentialsPassword();
                user = userDao.findUserByEmailAndPassword(tmpEmail, tmpPassword);
            } else {
                user = userDao.findUserByEmailAndPassword(email, password);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to login user with email:" + email, e);
            throw new LoginException();
        }

        if (user == null) {
            LOGGER.error("Failed to login user with email:" + email);
            throw new LoginException();
        }
        return user;

    }
}
