package org.example.mbean.user;


import org.example.entity.Order;
import org.example.entity.Pizza;
import org.example.entity.User;
import org.example.service.OrderService;
import org.example.service.PizzaService;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class OrderPizzaMBean implements Serializable {
    private User loggedInUser;
    private List<Order> orderList;//own
    private List<Pizza> pizzaList;
    private Order currentOrder = new Order();

    @Inject
    private OrderService orderService;

    @Inject
    private PizzaService pizzaService;

    @PostConstruct
    private void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        loggedInUser = (User) session.getAttribute("user");
        load();

    }

    private void load() {
        orderList = loggedInUser.getOrders();
        pizzaList = pizzaService.getAll();
    }

    public void SaveOrder() {
        orderService.add(currentOrder);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }
}
