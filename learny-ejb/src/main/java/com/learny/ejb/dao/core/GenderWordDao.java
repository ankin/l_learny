package com.learny.ejb.dao.core;

import java.util.List;

public interface GenderWordDao<T> extends Dao<T> {

    List<T> findByValue(String value);

}
