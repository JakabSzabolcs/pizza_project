package org.example.mbean.admin;

import jdk.jfr.Name;
import org.apache.bcel.generic.FLOAD;
import org.example.entity.Pizza;
import org.example.mbean.LoginMBean;
import org.example.service.OrderService;
import org.example.service.PizzaService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PizzaMBean extends LoginMBean implements Serializable {

    private List<Pizza> pizzaList;
    private Pizza selectedPizza = new Pizza();


    @Inject
    private PizzaService pizzaService;

    @PostConstruct
    public void init() {
        load();
    }

    private void load() {
        pizzaList = pizzaService.getAll();
    }


    public void save() {
        if (selectedPizza.getId() == null) {
            selectedPizza.setCreatorUser(getLoggedInUser());
            pizzaService.add(selectedPizza);
        } else {
            selectedPizza.setModifierUser(getLoggedInUser());
            selectedPizza.setModificationDate(new java.sql.Timestamp(System.currentTimeMillis()));
            pizzaService.update(selectedPizza);

        }
        infoMessage("Pizza saved successfully.");
        load();
        initNewPizza();

    }

    public void remove() {
        try {
            pizzaService.remove(selectedPizza);
            infoMessage("Pizza removed successfully.");
            load();
            initNewPizza();
        } catch (Exception e) {
            errorMessage("Pizza cannot be removed.");
        }
    }

    public void initNewPizza() {
        selectedPizza = new Pizza();
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public Pizza getSelectedPizza() {
        return selectedPizza;
    }

    public void setSelectedPizza(Pizza selectedPizza) {
        this.selectedPizza = selectedPizza;
    }
}
