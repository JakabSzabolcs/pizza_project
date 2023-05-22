package org.example.dao.impl;


import org.example.dao.CoreDAO;
import org.example.entity.AbstractEntity;
import org.example.entity.CoreEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class CoreDAOImpl<T extends CoreEntity> implements CoreDAO<T>
{
    @PersistenceContext(unitName = "PizzaPersistence")
    protected EntityManager em;

    @Override
    public List<T> getAll()
    {
        return em.createQuery("select n from " + getManagedClass().getSimpleName() + " n", getManagedClass()).getResultList();
    }

    @Override
    public void add(T entity)
    {
        em.persist(entity);
    }

    @Override
    public void remove(Long id)
    {
        em.remove(findById(id));
    }

    @Override
    public T findById(Long id)
    {
        return em.find(getManagedClass(), id);
    }

    @Override
    public void update(T entity)
    {
        em.merge(entity);
    }


    protected abstract Class<T> getManagedClass();

    protected EntityManager getEntityManager()
    {
        return em;
    }




}
