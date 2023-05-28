package org.example.dao;

import org.example.entity.Courier;
import org.example.entity.Order;

import java.util.List;

public interface CourierDAO extends CoreDAO<Courier>{


    List<Order> getOrdersByCourierId(Long id);

}
