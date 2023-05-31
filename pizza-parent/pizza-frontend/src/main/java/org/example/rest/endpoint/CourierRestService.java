package org.example.rest.endpoint;

import org.example.entity.Courier;
import org.example.rest.model.CourierModel;
import org.example.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/courier")
public class CourierRestService extends CoreRestService<Courier, CourierModel> {

    @Inject
    private UserService userService;

    @Override
    protected Class<Courier> getManagedClass() {
        return Courier.class;
    }

    @Override
    protected Courier dtoToEntity(Courier entity, CourierModel model) {
        entity.setId(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setModifierUser(userService.findById(model.getModifierUserId()));
        entity.setModificationDate(model.getModificationDate());
        entity.setCreatorUser(userService.findById(model.getCreatorUserId()));
        entity.setCreationDate(model.getCreationDate());
        return entity;
    }

    @Override
    protected CourierModel entityToDTO(Courier entity) {
        CourierModel model = new CourierModel();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setModifierUserId(entity.getModifierUser().getId());
        model.setModificationDate(entity.getModificationDate());
        model.setCreatorUserId(entity.getCreatorUser().getId());
        model.setCreationDate(entity.getCreationDate());
        return model;
    }
}
