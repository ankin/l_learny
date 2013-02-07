package com.learny.ejb.dao.core;

import java.util.List;

public interface Dao<T> {

    T saveOrUpdate(T entity);

    void remove(T entity);

    T findById(Long id);

    List<T> findAll();

    T findByUuid(String uuid);

}
