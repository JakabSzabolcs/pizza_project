package org.example.rest.endpoint;

import org.example.entity.Pizza;
import org.example.rest.model.PizzaModel;

import javax.ws.rs.Path;

@Path("/pizza")
public class PizzaRestService extends CoreRestService<Pizza, PizzaModel>
{

    @Override
    protected Pizza dtoToEntity(Pizza entity, PizzaModel model) {
        entity.setName(model.getName());
        entity.setDetails(model.getDetails());
        entity.setPrice(model.getPrice());
        return entity;
    }

    @Override
    protected PizzaModel entityToDTO(Pizza entity) {
        PizzaModel model = new PizzaModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDetails(entity.getDetails());
        model.setPrice(entity.getPrice());
        return model;
    }

    @Override
    protected Class<Pizza> getManagedClass() {
        return Pizza.class;
    }
}
