package org.example.service.impl;

import org.example.dao.CourierDAO;
import org.example.entity.AbstractEntity;
import org.example.entity.Courier;
import org.example.entity.Order;
import org.example.service.CourierService;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CourierServiceImpl extends AbstractServiceImpl<Courier> implements CourierService {

    @Inject
    private CourierDAO courierDAO;

    @Override
    public List<Order> getOrdersByCourierId(Long id) {
        return courierDAO.getOrdersByCourierId(id);
    }
}
