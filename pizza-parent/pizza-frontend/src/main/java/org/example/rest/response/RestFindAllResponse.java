package org.example.rest.response;

public class RestFindAllResponse<T> {
    private java.util.List<T> models;

    public RestFindAllResponse(){
    }

    public RestFindAllResponse(java.util.List<T> models){
        this.models = models;
    }

    public java.util.List<T> getModels(){
        return models;
    }

    public void setModels(java.util.List<T> models){
        this.models = models;
    }
}
