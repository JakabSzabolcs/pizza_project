package org.example.dao;

import org.example.entity.AbstractEntity;
import org.example.entity.CoreEntity;

import java.util.List;

public interface CoreDAO <T extends CoreEntity> {
    List<T> getAll();

    void add(T entity);

    void remove(Long id);

    T findById(Long id);

    void update(T entity);

}
