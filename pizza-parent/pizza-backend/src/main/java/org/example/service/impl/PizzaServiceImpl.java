package org.example.service.impl;

import org.example.entity.Pizza;
import org.example.service.PizzaService;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@Stateless
public class PizzaServiceImpl extends AbstractServiceImpl<Pizza> implements PizzaService {
}
