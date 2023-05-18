package org.example.dao.impl;

import org.example.dao.CourierDAO;
import org.example.dao.OrderDAO;
import org.example.entity.Courier;
import org.example.entity.Order;

import javax.ejb.Stateless;

@Stateless
public class CourierDAOImpl extends CoreDAOImpl<Courier> implements CourierDAO
{

    @Override
    protected Class<Courier> getManagedClass() {
        return Courier.class;
    }
}
