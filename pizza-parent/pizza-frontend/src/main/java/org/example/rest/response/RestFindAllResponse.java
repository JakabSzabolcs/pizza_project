package org.example.rest.response;

import java.util.List;

public class RestFindAllResponse<T> {
    private java.util.List<T> models;

    public RestFindAllResponse(){
    }

    public RestFindAllResponse(java.util.List<T> models){
        this.models = models;
    }

    public List<T> getModels(){
        return models;
    }

    public void setModels(java.util.List<T> models){
        this.models = models;
    }
}
