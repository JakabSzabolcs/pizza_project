package org.example.dao;

import org.example.entity.Order;
import org.example.service.CoreService;

import java.util.List;

public interface OrderDAO extends CoreDAO<Order>
{
    List<Order> getOrdersByCourierId(Long id);
}
