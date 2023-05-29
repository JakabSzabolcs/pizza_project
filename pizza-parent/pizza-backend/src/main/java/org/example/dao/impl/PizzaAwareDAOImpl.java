package org.example.dao.impl;

import org.example.dao.PizzaAwareDAO;
import org.example.entity.AbstractEntity;
import org.example.entity.Pizza;
import org.example.service.OrderService;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class PizzaAwareDAOImpl <T extends AbstractEntity> extends CoreDAOImpl<T> implements PizzaAwareDAO<T>
{
    @Override
    public List<T> findByPizzaID(Long pizzaId) {
        TypedQuery<T> query = getEntityManager().createQuery("select n from " + getManagedClass().getSimpleName() + " n where n.pizza.id=:id", getManagedClass());
        query.setParameter("id", pizzaId);
        return query.getResultList();
    }
}
