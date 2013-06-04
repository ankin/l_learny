package com.learny.ejb.dao.core;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class AbstractDao<T> implements Dao<T> {

    @PersistenceContext(unitName = "LearnyEntityManager")
    private EntityManager entityManager;

    private Class<T> persistentClass;

    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public final EntityManager getEntityManager() {
        return entityManager;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public T saveOrUpdate(T entity) {
        return getEntityManager().merge(entity);
    }

    @Override
    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public T findById(Long id) {
        return getEntityManager().find(persistentClass, id);
    }

    @Override
    public final List<T> findAll() {
        final String query = "select o from " + persistentClass.getSimpleName() + " o";
        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    public final T findByUuid(String uuid) {
        try {
            final String query = "select o from " + getPersistentClass().getSimpleName() + " o where o.uuid=:uuid";
            final T result = (T) getEntityManager().createQuery(query).setParameter("uuid", uuid).getSingleResult();
            return result;
        } catch (final NoResultException ex) {
            return null;
        }
    }

}
