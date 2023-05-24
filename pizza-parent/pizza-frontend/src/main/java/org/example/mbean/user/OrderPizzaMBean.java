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
import java.time.LocalDateTime;
import java.util.List;

@ViewScoped
@Named
public class OrderPizzaMBean implements Serializable {
    private User loggedInUser;
    private List<Order> orderList;//own
    private Order currentOrder = new Order();
    private List<Pizza> pizzaList;
    private Pizza selectedPizza = new Pizza();
    private int totalPrice;
    private LocalDateTime minDate = LocalDateTime.now();

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
        if (currentOrder.getPizzas().isEmpty()) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", "Please select at least one pizza");
            return;
        } else {
            currentOrder.setCreatorUser(loggedInUser);
            orderService.add(currentOrder);
            load();
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("success", "Order has been saved successfully");
        currentOrder = new Order();
        load();
    }

    public void addPizzaToOrder(Pizza pizza) {
        if (pizza != null) {
            currentOrder.getPizzas().add(pizza);
        } else FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", "Please select a pizza");
    }

    public void removePizzaFromOrder(Pizza pizza) {
        currentOrder.getPizzas().remove(pizza);
    }

    public void clearOrder() {
        currentOrder = new Order();
    }

    public int getTotalPrice() {
        totalPrice = 0;
        for (Pizza pizza : currentOrder.getPizzas()) {
            totalPrice += pizza.getPrice();
        }
        return totalPrice;
    }

    public void initNewPizza() {
        selectedPizza = new Pizza();
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

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Pizza getSelectedPizza() {
        return selectedPizza;
    }

    public void setSelectedPizza(Pizza selectedPizza) {
        this.selectedPizza = selectedPizza;
    }


    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDateTime minDate) {
        this.minDate = minDate;
    }
}
