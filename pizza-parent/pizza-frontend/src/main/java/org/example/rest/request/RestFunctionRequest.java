package org.example.rest.request;

public class RestFunctionRequest<T> {
    private T model;

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
