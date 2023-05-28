package org.example.rest.endpoint;

import org.example.entity.CoreEntity;
import org.example.rest.model.ApiModel;
import org.example.rest.request.RestFunctionRequest;
import org.example.rest.response.RestFindAllResponse;
import org.example.rest.response.RestFindByIdResponse;
import org.example.service.CoreService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.module.ResolvedModule;
import java.util.ArrayList;
import java.util.List;

public abstract class CoreRestService<T extends CoreEntity, M extends ApiModel> {

    @Inject
    private CoreService<T> coreService;


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {

        T entity = coreService.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new RestFindByIdResponse<>(entityToDTO(entity))).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Long id) {

        T entity = coreService.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        coreService.remove(entity);
        return Response.ok().build();
    }


    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        List<M> list = new ArrayList<>();
        coreService.getAll().forEach(car -> {
            list.add(entityToDTO(car));
        });

        return Response.ok(new RestFindAllResponse<>(list)).build();
    }


    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(RestFunctionRequest<M> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        T entity = dtoToEntity(request.getModel());
        coreService.add(entity);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(entity))).build();

    }

    @PUT
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(RestFunctionRequest<M> request) {
        if (request.getModel() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        T shi = coreService.findById(request.getModel().getId());
        if (shi == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dtoToEntity(shi, request.getModel());
        coreService.update(shi);

        return Response.ok(new RestFindByIdResponse<>(entityToDTO(shi))).build();

    }


    private T dtoToEntity(M model){
        try {
            return dtoToEntity(getManagedClass().newInstance(), model);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected abstract T dtoToEntity(T entity, M model);

    //mapstruct
    protected abstract M entityToDTO(T entity);

    protected abstract Class<T> getManagedClass();


}
