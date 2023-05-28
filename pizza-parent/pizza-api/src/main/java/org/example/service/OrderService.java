package org.example.service;

import org.example.entity.Order;

import java.util.List;

public interface OrderService extends CoreService<Order>
{
    List<Order> getOrdersByCourierId(Long id);

}
