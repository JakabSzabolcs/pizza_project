package org.example.dao;

import org.example.entity.AbstractEntity;
import org.example.entity.Pizza;

import java.util.List;

public interface PizzaAwareDAO<T extends AbstractEntity> extends CoreDAO<T>{

    List<T> findByPizzaID(Long pizzaId);
}
