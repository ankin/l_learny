package com.learny.ejb.dao.local;

import javax.ejb.Local;

import com.learny.ejb.dao.core.Dao;
import com.learny.persistence.entity.User;

@Local
public interface UserDaoLocal extends Dao<User> {

    User findUserByEmailAndPassword(String email, String password);

}
