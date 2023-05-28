package org.example.service.impl;

import org.example.dao.OrderDAO;
import org.example.entity.Order;
import org.example.service.OrderService;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class OrderServiceImpl extends AbstractServiceImpl<Order> implements OrderService {


    @Inject
    private OrderDAO dao;


    @Override
    public List<Order> getOrdersByCourierId(Long id) {
        return dao.getOrdersByCourierId(id);
    }
}
