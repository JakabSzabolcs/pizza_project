package org.example.service.impl;

import org.example.entity.Order;
import org.example.service.OrderService;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@Stateless
public class OrderServiceImpl extends AbstractServiceImpl<Order> implements OrderService {

}
