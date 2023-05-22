package org.example.dao.impl;

import org.example.dao.OrderDAO;
import org.example.entity.Order;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;

@Stateless
public class OrderDAOImpl extends CoreDAOImpl<Order> implements OrderDAO
{

    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }
}
