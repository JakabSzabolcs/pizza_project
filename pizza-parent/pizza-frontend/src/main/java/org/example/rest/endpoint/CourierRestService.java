package org.example.rest.endpoint;

import org.example.entity.Courier;
import org.example.rest.model.CourierModel;

import javax.ws.rs.Path;

@Path("/courier")
public class CourierRestService extends CoreRestService<Courier, CourierModel> {

    @Override
    protected Class<Courier> getManagedClass() {
        return Courier.class;
    }

    @Override
    protected Courier dtoToEntity(Courier entity, CourierModel model) {
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setPhoneNumber(model.getPhoneNumber());
        return entity;
    }

    @Override
    protected CourierModel entityToDTO(Courier entity) {
        CourierModel model = new CourierModel();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setPhoneNumber(entity.getPhoneNumber());
        return model;
    }
}
