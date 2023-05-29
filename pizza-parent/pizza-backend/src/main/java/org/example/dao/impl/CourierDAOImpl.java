package org.example.dao.impl;

import org.example.dao.CourierDAO;
import org.example.entity.Courier;
import org.example.entity.Order;

import javax.ejb.Stateless;
import java.util.List;


@Stateless
public class CourierDAOImpl extends CoreDAOImpl<Courier> implements CourierDAO
{

    @Override
    protected Class<Courier> getManagedClass() {
        return Courier.class;
    }

    @Override
    public List<Order> getOrdersByCourierId(Long id) {
        return em.createQuery("select o from Order o where o.courier.id = :id", Order.class)
                .setParameter("id", id)
                .getResultList();
    }
}
