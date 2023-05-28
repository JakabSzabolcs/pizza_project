package org.example.service.impl;

import org.example.dao.CourierAwareDAO;
import org.example.entity.AbstractEntity;
import org.example.service.CourierAwareService;

import javax.inject.Inject;
import java.util.List;

public abstract class CourierAwareServiceImpl<T extends AbstractEntity> extends AbstractServiceImpl<T> implements CourierAwareService<T> {
    @Inject
    private CourierAwareDAO<T> courierAwareDAO;

    @Override
    public List<T> findByCourierID(Long courierId) {
        return courierAwareDAO.findByCourierID(courierId);
    }

}
