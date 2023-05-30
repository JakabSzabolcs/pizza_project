package org.example.mbean.admin;


import org.example.entity.Courier;
import org.example.entity.Order;

import org.example.entity.User;
import org.example.mbean.LoginMBean;
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
public class OrderMBean extends LoginMBean implements Serializable {
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
    }

    private void load() {
        courierList = courierService.getAll();
        list = orderService.getAll();
    }


    public void save() {


        if (selectedCourierId != null) {
            selectedOrder.setCourier(courierService.findById(selectedCourierId));
        } else {
            selectedOrder.setCourier(null);
        }
        selectedOrder.setModifierUser(getLoggedInUser());
        selectedOrder.setModificationDate(new Timestamp(System.currentTimeMillis()));
        if (selectedOrder.getId() == null) {
            selectedOrder.setCreatorUser(getLoggedInUser());
            orderService.add(selectedOrder);
        } else {
            orderService.update(selectedOrder);
        }

        infoMessage("Successful save: " + selectedOrder.getId().toString());
        load();
        initNewOrder();
        inFunction = false;
    }


    public void initNewOrder() {
        selectedOrder = new Order();
        selectedCourierId = null;
        inFunction = true;
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
