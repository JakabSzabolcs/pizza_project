package org.example.dao;

import org.example.entity.AbstractEntity;

import java.util.List;

public interface CourierAwareDAO<T extends AbstractEntity> extends CoreDAO<T> {

    List<T> findByCourierID(Long courierId);
}
