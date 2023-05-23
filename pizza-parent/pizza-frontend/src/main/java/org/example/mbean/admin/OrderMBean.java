package org.example.mbean.admin;


import org.example.entity.Courier;
import org.example.entity.Order;

import org.example.service.CourierService;
import org.example.service.OrderService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class OrderMBean implements Serializable {
    private List<Order> list = new ArrayList<>();
    private List<Courier> courierList;
    private Order selectedOrder = new Order();
    private boolean inFunction;
    private Long selectedCourierId;

    @Inject
    private OrderService orderService;

    @Inject
    private CourierService courierService;

    @PostConstruct
    private void init() {
        load();
        courierList = courierService.getAll();
    }

    private void load() {
        list = orderService.getAll();
    }


    public void save() {

        selectedOrder.setModificationDate(new Timestamp(System.currentTimeMillis()));

        if (selectedCourierId != null) {
            selectedOrder.setCourier(courierService.findById(selectedCourierId));
        } else {
            selectedOrder.setCourier(null);
        }

        if (selectedOrder.getId() == null) {
            orderService.add(selectedOrder);
        } else {
            orderService.update(selectedOrder);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save: " + selectedOrder.getId()));
        load();
        initNewOrder();
        inFunction = false;
    }


    public void initNewOrder() {
        selectedOrder = new Order();
        selectedCourierId = null;
        inFunction = true;
    }

    public void remove() {
        orderService.remove(selectedOrder);
        load();
        initNewOrder();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful delete: " + selectedOrder.getId()));
        inFunction = false;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public List<Courier> getCourierList() {
        return courierList;
    }

    public List<Order> getList() {
        return list;
    }

    public void setInFunction(boolean inFunction) {
        this.inFunction = inFunction;
    }

    public boolean isInFunction() {
        return inFunction;
    }

    public Long getSelectedCourierId() {
        return selectedCourierId;
    }

    public void setSelectedCourierId(Long selectedCourierId) {
        this.selectedCourierId = selectedCourierId;
    }
}
