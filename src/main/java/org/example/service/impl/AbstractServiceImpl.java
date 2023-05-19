package org.example.service.impl;

import org.example.dao.CoreDAO;
import org.example.entity.AbstractEntity;
import org.example.service.CoreService;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

public abstract class AbstractServiceImpl<T extends AbstractEntity> implements CoreService<T> {

    @Inject
    private CoreDAO<T> entityDAO;

    @TransactionAttribute(TransactionAttributeType.NEVER)
    @Override
    public List<T> getAll() {
        return entityDAO.getAll();
    }

    @Override
    public void add(T entity) {
        entityDAO.add(entity);
    }

    @Override
    public void update(T entity) {
        entityDAO.update(entity);
    }

    @Override
    public void remove(T entity) {
        entityDAO.remove(entity.getId());
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    @Override
    public T findById(Long id) {
        return entityDAO.findById(id);
    }
}
