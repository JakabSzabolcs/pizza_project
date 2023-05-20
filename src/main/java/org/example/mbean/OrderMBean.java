package org.example.mbean;
import jdk.jfr.Name;
import org.example.entity.Order;
import org.example.entity.Pizza;
import org.example.service.OrderService;
import org.example.service.PizzaService;

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

    @Inject
    private OrderService orderService;

    @Inject
    private PizzaService pizzaService;

    private Pizza selectedPizza;
    private Order newOrder;
    private List<Pizza> pizzas;
    private List<Order> orders;

    @PostConstruct
    public void init() {
        newOrder = new Order();
        pizzas = new ArrayList<>();
        orders = orderService.getAll();
    }

    public void addOrder() {
        newOrder.setPizzas(pizzas);
        orderService.add(newOrder);
        newOrder = new Order();
        pizzas.clear();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rendelés sikeresen hozzáadva.", null));
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    // getterek és setterek

    public Order getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Order newOrder) {
        this.newOrder = newOrder;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public PizzaService getPizzaService() {
        return pizzaService;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Pizza getSelectedPizza() {
        return selectedPizza;
    }

    public void setSelectedPizza(Pizza selectedPizza) {
        this.selectedPizza = selectedPizza;
    }
}
