package org.example.rest.endpoint;

import org.example.entity.Pizza;
import org.example.rest.model.PizzaModel;
import org.example.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/pizza")
public class PizzaRestService extends CoreRestService<Pizza, PizzaModel>
{

    @Inject
    private UserService userService;
    @Override
    protected Pizza dtoToEntity(Pizza entity, PizzaModel model) {
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDetails(model.getDetails());
        entity.setPrice(model.getPrice());
        entity.setModifierUser(userService.findById(model.getModifierUserId()));
        entity.setModificationDate(model.getModificationDate());
        entity.setCreatorUser(userService.findById(model.getCreatorUserId()));
        entity.setCreationDate(model.getCreationDate());
        return entity;
    }

    @Override
    protected PizzaModel entityToDTO(Pizza entity) {
        PizzaModel model = new PizzaModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDetails(entity.getDetails());
        model.setPrice(entity.getPrice());
        model.setModifierUserId(entity.getModifierUser().getId());
        model.setModificationDate(entity.getModificationDate());
        model.setCreatorUserId(entity.getCreatorUser().getId());
        model.setCreationDate(entity.getCreationDate());
        return model;
    }

    @Override
    protected Class<Pizza> getManagedClass() {
        return Pizza.class;
    }
}
