package com.learny.ejb.dao.core;

import java.util.List;

import javax.persistence.NoResultException;

public class AbstractGenderWordDao<T> extends AbstractDao<T> implements GenderWordDao<T> {

    @Override
    public final List<T> findByValue(String value) {
        try {
            final String query = "select o from " + getPersistentClass().getSimpleName() + " o where o.value=:value";
            final List<T> result = (List<T>) getEntityManager().createQuery(query).setParameter("value", value)
                    .getResultList();
            return result;
        } catch (final NoResultException ex) {
            return null;
        }
    }

}
