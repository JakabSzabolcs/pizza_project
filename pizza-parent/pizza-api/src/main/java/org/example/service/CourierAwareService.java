package org.example.service;

import org.example.entity.AbstractEntity;

import java.util.List;

public interface CourierAwareService<T extends AbstractEntity>
{
    List<T> findByCourierID(Long courierId);
}
