package org.example.mbean.user;


import org.example.entity.Order;
import org.example.entity.Pizza;
import org.example.entity.User;
import org.example.mbean.LoginMBean;
import org.example.service.OrderService;
import org.example.service.PizzaService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ViewScoped
@Named
public class OrderPizzaMBean extends LoginMBean implements Serializable {
    private User loggedInUser;
    private List<Order> list;//own
    private Order currentOrder = new Order();

    private Pizza selectedPizza = new Pizza();


    @Inject
    private OrderService orderService;

    @Inject
    private PizzaService pizzaService;

    private List<Pizza> pizzaList = new ArrayList<>();

    @PostConstruct
    private void init() {
        pizzaList = pizzaService.getAll();
        loggedInUser = getLoggedInUser();
        load();
    }

    private void load() {
        loggedInUser = super.getLoggedInUser();
        list = loggedInUser.getOrders();
    }

    public void SaveOrder() {
        if (currentOrder.getPizzas().isEmpty()) {
            errorMessage("Please select at least one pizza");
            return;
        } else {
            if (currentOrder.getDeliveryDate().before(new Date())) {
                errorMessage("Please select a valid date");
                return;
            }
            currentOrder.setCreatorUser(loggedInUser);
            orderService.add(currentOrder);
            load();
        }
        infoMessage("Order saved successfully");
        currentOrder = new Order();
        load();
    }

    public void addPizzaToOrder(Pizza pizza) {
        if (pizza != null) {
            currentOrder.getPizzas().add(pizza);
        } else
            errorMessage("Please select a pizza");
    }

    public void removePizzaFromOrder(Pizza pizza) {
        currentOrder.getPizzas().remove(pizza);
    }

    public void clearOrder() {
        currentOrder = new Order();
    }

    public int TotalPrice(Order order) {
        int totalPrice = 0;
        for (Pizza pizza : order.getPizzas()) {
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

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
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


    public PizzaService getPizzaService() {
        return pizzaService;
    }

    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
}
