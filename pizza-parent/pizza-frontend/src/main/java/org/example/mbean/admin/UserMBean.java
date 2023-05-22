package org.example.mbean.admin;

import org.example.entity.Order;
import org.example.entity.User;
import org.example.entity.UserRole;
import org.example.service.OrderService;
import org.example.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Named
@ViewScoped
public class UserMBean implements Serializable {
    private List<User> list = new ArrayList<>();
    private List<Order> orderList;
    private User selectedUser = new User();
    private Order selectedOrder = new Order();
    private boolean inFunction;
    private boolean isAdmin;

    @EJB
    private UserService userService;

    @EJB
    private OrderService orderService;

    @PostConstruct
    private void init() {
        load();
        orderList = orderService.getAll();
    }

    private void load() {
        list = userService.getAll();
    }

    public List<User> getList() {
        return list;
    }

    public void save(){
        if (selectedUser.getId() == null) {
            if(isAdmin){
                selectedUser.setRole(UserRole.ADMIN);
            } else {
                selectedUser.setRole(UserRole.USER);
            }
            userService.add(selectedUser);
        } else {
            userService.update(selectedUser);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save: " + selectedUser.getUsername()));
        load();
        initNewUser();
        inFunction = false;
    }

    public void initNewUser() {
        selectedUser = new User();
        inFunction = true;
    }

    public void selectedUser(User user) {
        selectedUser = user;
    }

    public void remove() {
        userService.remove(selectedUser);
        load();
        initNewUser();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful remove"));
        inFunction = false;
    }

    public void removeOrder() {
        orderService.remove(selectedOrder);
        load();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful remove"));
        inFunction = false;
    }

    public void initNewOrder() {
        selectedOrder = new Order();
        inFunction = true;
    }

    public void cancel() {
        initNewUser();
        inFunction = false;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public boolean isInFunction() {
        return inFunction;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }
}
