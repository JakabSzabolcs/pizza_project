package org.example.mbean.user;

import org.example.entity.Order;
import org.example.entity.Pizza;
import org.example.entity.User;
import org.example.service.OrderService;
import org.example.service.PizzaService;
import org.example.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ViewScoped
@Named
public class PizzaMBean implements Serializable {
    private List<Pizza> list = new ArrayList<>();
    private Pizza selectedPizza;
    private List<Pizza> pizzasOfCurrentOrder = new ArrayList<>();
    private Order currentOrder = new Order();
    private List<Order> orderListOfCurrentUser;
    private User currentUser = new User();

    @Inject
    private PizzaService pizzaService;

    @Inject
    private OrderService orderService;


    @PostConstruct
    private void init() {
        load();
        selectedPizza = new Pizza();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        currentUser = (User) session.getAttribute("user");
        orderListOfCurrentUser = orderService.getOrdersByUserId(currentUser.getId());
    }

    public List<Pizza> getList() {
        return list;
    }

    public void save() {
        if (selectedPizza.getId() == null) {
            pizzaService.add(selectedPizza);
        } else {
            pizzaService.update(selectedPizza);
        }
        load();
    }

    public void load() {
        list = pizzaService.getAll();
    }

    public void delete(Pizza pizza) {
        pizzaService.remove(pizza);
        load();
    }

    public void addPizzaToOrder() {
        pizzasOfCurrentOrder.add(selectedPizza);
        currentOrder.getPizzas().add(selectedPizza);
        selectedPizza = null;
    }

    public void removePizzaFromOrder(Pizza pizza) {
        pizzasOfCurrentOrder.remove(pizza);
        currentOrder.getPizzas().remove(pizza);
    }

    public void saveOrder() {
        currentOrder.setCreatorUser(currentUser);
        currentOrder.setPizzas(pizzasOfCurrentOrder);
        currentOrder.setDeliveryDate(new Date());
        orderService.add(currentOrder);
        pizzasOfCurrentOrder.clear();
        currentOrder = new Order();
    }

    public List<Pizza> getPizzaOrder() {
        return pizzasOfCurrentOrder;
    }

    public void setPizzaOrder(List<Pizza> pizzaOrder) {
        this.pizzasOfCurrentOrder = pizzaOrder;
    }

    public Pizza getSelectedPizza() {
        return selectedPizza;
    }

    public void setSelectedPizza(Pizza selectedPizza) {
        this.selectedPizza = selectedPizza;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public List<Order> getOrderListOfCurrentUser() {
        return orderListOfCurrentUser;
    }

    // sum of all pizzas in order
    public double getTotalPrice(Order order) {
        double sum = 0;
        for (Pizza pizza : order.getPizzas()) {
            sum += pizza.getPrice();
        }
        return sum;
    }

}
