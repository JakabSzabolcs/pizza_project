package org.example.rest.endpoint;

import org.example.entity.AbstractEntity;
import org.example.entity.Courier;
import org.example.rest.model.ApiModel;
import org.example.rest.response.RestFindAllResponse;
import org.example.service.CourierService;
import org.example.service.PizzaAwareService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public abstract class PizzaAwareRestService<T extends AbstractEntity, M extends ApiModel> extends CoreRestService<T, M> {

    @Inject
    private PizzaAwareService<T> service;

    @GET
    @Path("/pizza/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPizzaId(@PathParam("id") Long pizzaId) {

            List<M> list = new ArrayList<>();
            service.findByPizzaID(pizzaId).forEach(pizza -> {
                list.add(entityToDTO(pizza));
            });

            return Response.ok(new RestFindAllResponse<>(list)).build();
        }



}
