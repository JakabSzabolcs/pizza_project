package org.example.service.impl;

import org.example.dao.PizzaAwareDAO;
import org.example.entity.AbstractEntity;
import org.example.service.PizzaAwareService;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;


public abstract class PizzaAwareServiceImpl<T extends AbstractEntity> extends CoreServiceImpl<T> implements PizzaAwareService<T> {

    @Inject
    private PizzaAwareDAO<T> pizzaAwareDAO;


    @Override
    public List<T> findByPizzaID(Long pizzaId) {
        return pizzaAwareDAO.findByPizzaID(pizzaId);
    }
}
