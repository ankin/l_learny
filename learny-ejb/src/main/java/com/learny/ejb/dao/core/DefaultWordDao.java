package com.learny.ejb.dao.core;


public interface DefaultWordDao<T> extends Dao<T> {

    T findByValue(String value);

}
