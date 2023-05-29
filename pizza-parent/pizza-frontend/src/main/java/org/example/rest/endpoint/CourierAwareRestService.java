package org.example.rest.endpoint;

import org.example.entity.AbstractEntity;
import org.example.rest.model.ApiModel;
import org.example.rest.response.RestFindAllResponse;
import org.example.service.CourierAwareService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public abstract class CourierAwareRestService<T extends AbstractEntity, M extends ApiModel> extends CoreRestService<T, M> {

    @Inject
    private CourierAwareService<T> service;

    @GET
    @Path("/courier/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCourierId(@PathParam("id") Long courierId) {

        List<M> list = new ArrayList<>();
        service.findByCourierID(courierId).forEach(courier -> {
            list.add(entityToDTO(courier));
        });

        return Response.ok(new RestFindAllResponse<>(list)).build();
    }
}
