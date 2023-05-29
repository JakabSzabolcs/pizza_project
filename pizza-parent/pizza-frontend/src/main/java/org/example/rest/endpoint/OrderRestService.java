package org.example.rest.endpoint;

import org.example.entity.Order;
import org.example.entity.Pizza;
import org.example.rest.model.OrderModel;
import org.example.rest.model.PizzaModel;
import org.example.service.CourierService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.List;

@Path("/order")
public class OrderRestService extends CoreRestService<Order, OrderModel>{

    @Inject
    private CourierService courierService;

    @Override
    protected Order dtoToEntity(Order entity, OrderModel model) {
        entity.setCreationDate(model.getCreationDate());
        entity.setCity(model.getCity());
        entity.setStreet(model.getStreet());
        entity.setStreetType(model.getStreetType());
        entity.setHouseNumber(model.getHouseNumber());
        entity.setCourier(courierService.findById(model.getCourierId()));
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
        model.setCourierId(entity.getCourier().getId());
        model.setPizzas(entity.getPizzas());
        return model;
    }

    @Override
    protected Class<Order> getManagedClass() {
        return Order.class;
    }
}
