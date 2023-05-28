package org.example.service;

import org.example.entity.Courier;
import org.example.entity.Order;

import java.util.List;

public interface CourierService extends CoreService<Courier>
{

    List<Order> getOrdersByCourierId(Long id);
}
