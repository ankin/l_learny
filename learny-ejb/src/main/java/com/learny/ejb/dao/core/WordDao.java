package com.learny.ejb.dao.core;


public interface WordDao<T> extends Dao<T> {

    T findByValue(final String value);

}
