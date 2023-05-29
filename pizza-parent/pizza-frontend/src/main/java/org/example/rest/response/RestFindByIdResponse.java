package org.example.rest.response;

public class RestFindByIdResponse<T>{
    private T model;

    public RestFindByIdResponse(){
    }

    public RestFindByIdResponse(T model){
        this.model = model;
    }

    public T getModel(){
        return model;
    }

    public void setModel(T model){
        this.model = model;
    }
}
