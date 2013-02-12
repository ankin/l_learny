package com.learny.ejb.service.local;

import javax.ejb.Local;

import com.learny.ejb.service.exception.LoginException;
import com.learny.persistence.entity.User;

@Local
public interface UserBeanLocal {

    User login(String email, String password) throws LoginException;

}
