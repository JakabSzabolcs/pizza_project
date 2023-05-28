package org.example.mbean.user;

import jdk.jfr.Name;
import org.example.entity.Order;
import org.example.entity.User;
import org.example.mbean.LoginMBean;
import org.example.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserOrdersMBean extends LoginMBean implements Serializable {
    private User loggedInUser;
    private List<Order> orderList;

    @Inject
    private UserService userService;
    @PostConstruct
    private void init() {
        load();
    }

    private void load() {
        loggedInUser = super.getLoggedInUser();
        orderList = userService.findByUsername(loggedInUser.getUsername()).getOrders();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
