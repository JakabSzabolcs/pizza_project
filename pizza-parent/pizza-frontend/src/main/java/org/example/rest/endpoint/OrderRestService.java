package org.example.rest.endpoint;

import org.example.entity.Order;
import org.example.entity.Pizza;
import org.example.rest.model.OrderModel;
import org.example.rest.model.PizzaModel;
import org.example.service.CourierService;
import org.example.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.List;

@Path("/order")
public class OrderRestService extends CoreRestService<Order, OrderModel>{

    @Inject
    private CourierService courierService;

    @Inject
    private UserService userService;



    @Override
    protected Order dtoToEntity(Order entity, OrderModel model) {
        entity.setCreationDate(model.getCreationDate());
        entity.setCity(model.getCity());
        entity.setStreet(model.getStreet());
        entity.setStreetType(model.getStreetType());
        entity.setHouseNumber(model.getHouseNumber());
        entity.setCourier(courierService.findById(model.getCourier().getId()));
        entity.setPizzas(model.getPizzas());
        entity.setModifierUser(userService.findById(model.getModifierUserId()));
        entity.setModificationDate(model.getModificationDate());
        entity.setCourier(courierService.findById(model.getCourier().getId()));

        return entity;
    }

    @Override
    protected OrderModel entityToDTO(Order entity) {
        OrderModel model = new OrderModel();
        model.setId(entity.getId());
        model.setCreationDate(entity.getCreationDate());
        model.setCity(entity.getCity());
        model.setStreet(entity.getStreet());
        model.setStreetType(entity.getStreetType());
        model.setHouseNumber(entity.getHouseNumber());
        model.setPizzas(entity.getPizzas());
        model.setModifierUserId(entity.getModifierUser().getId());
        model.setModificationDate(entity.getModificationDate());
        model.setCourier(entity.getCourier());
        return model;
    }

    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }
}
