package org.example.dao.impl;

import org.example.dao.OrderDAO;
import org.example.entity.Order;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import java.util.List;

@Stateless
public class OrderDAOImpl extends CoreDAOImpl<Order> implements OrderDAO
{

    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }



    @Override
    public List<Order> getOrdersByUserId(Long id) {
        return em.createQuery("select n from " + getManagedClass().getSimpleName() + " n where n.user.id = :id", getManagedClass())
                .setParameter("id", id).getResultList();
    }
}
