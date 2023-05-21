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
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class OrderMBean implements Serializable {
    private List<Order> list = new ArrayList<>();
    private List<Courier> courierList;
    private Order selectedOrder = new Order();
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

    public List<Order> getList() {
        return list;
    }

    public void save(){
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
    }

    public void initNewOrder() {
        selectedOrder = new Order();
        selectedCourierId = null;
    }

    public void remove(Order order) {
        orderService.remove(order);
        load();
        initNewOrder();
    }

    public void selectOrder(Order order) {
        selectedOrder = order;
        selectedCourierId = order.getCourier() != null ? order.getCourier().getId() : null;
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

    public Long getSelectedCourierId() {
        return selectedCourierId;
    }

    public void setSelectedCourierId(Long selectedCourierId) {
        this.selectedCourierId = selectedCourierId;
    }

    public void setSelectedOrder(Courier selectedCourier) {
        this.selectedOrder = selectedOrder;
        if(selectedOrder != null && selectedOrder.getCourier() != null) {
            selectedCourierId = selectedCourier.getId();
        }else {
            selectedCourierId = null;
        }
    }

}
