package org.example.mbean.user;



import org.example.entity.Order;
import org.example.entity.Pizza;
import org.example.service.OrderService;
import org.example.service.PizzaService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@ViewScoped
@Named
public class PizzaMBean implements Serializable {
    private List<Pizza> list = new ArrayList<>();
    private Pizza selectedPizza = new Pizza();
    private List<Pizza> pizzaOrder = new ArrayList<>();
    private Order currentOrder = new Order();


    @Inject
    private PizzaService pizzaService;

    @Inject
    private OrderService orderService;

    @PostConstruct
    private void init() {
        load();
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

    public void addPizzaToOrder(Pizza pizza) {

        pizzaOrder.add(pizza);
    }

    public void removePizzaFromOrder(Pizza pizza) {
        pizzaOrder.remove(pizza);
    }

    public void saveOrder() {
        currentOrder.setPizzas(pizzaOrder);
        orderService.add(currentOrder);
    }


}
