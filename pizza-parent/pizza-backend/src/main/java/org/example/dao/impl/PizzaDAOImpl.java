package org.example.dao.impl;

import org.example.dao.PizzaDAO;
import org.example.entity.Pizza;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class PizzaDAOImpl extends CoreDAOImpl<Pizza> implements PizzaDAO {

    @Override
    protected Class<Pizza> getManagedClass() {
        return Pizza.class;
    }
}
