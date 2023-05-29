package org.example.service;

import org.example.entity.AbstractEntity;
import org.example.entity.Pizza;

import java.util.List;

public interface PizzaAwareService<T extends AbstractEntity>
{
    List<T> findByPizzaID(Long pizzaId);
}
