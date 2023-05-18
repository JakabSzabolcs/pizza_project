package org.example.service;

import org.example.entity.AbstractEntity;

import java.util.List;

public interface CoreService<T extends AbstractEntity>
{
    List<T> getAll();

    void add(T entity);

    void update(T entity);

    void remove(T entity);

    T findById(Long id);
}
