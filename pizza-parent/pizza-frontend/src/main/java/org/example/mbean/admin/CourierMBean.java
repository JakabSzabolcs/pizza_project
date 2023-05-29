package org.example.mbean.admin;

import org.example.entity.Courier;
import org.example.entity.User;
import org.example.mbean.LoginMBean;
import org.example.service.CourierService;
import org.example.service.OrderService;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class CourierMBean extends LoginMBean implements Serializable {
    private List<Courier> list = new ArrayList<>();
    private User loggedInUser;
    private Courier selectedCourier = new Courier();

    @Inject
    private CourierService courierService;

    @Inject
    private OrderService orderService;

    @PostConstruct
    private void init() {
        loggedInUser = getLoggedInUser();
        load();
    }

    private void load() {
        list = courierService.getAll();
    }

    public void save() {

        if (selectedCourier.getFirstName() == null || selectedCourier.getLastName() == null || selectedCourier.getPhoneNumber() == null) {
            errorMessage("Please fill in all fields.");
        }

        if (selectedCourier.getId() == null) {
            selectedCourier.setCreatorUser(loggedInUser);
            courierService.add(selectedCourier);
        } else {
            selectedCourier.setModificationDate(new Timestamp(System.currentTimeMillis()));
            selectedCourier.setModifierUser(loggedInUser);
            courierService.update(selectedCourier);
        }
        infoMessage("Courier saved successfully.");
        load();
        initNewEntity();
    }


    public void initNewEntity() {
        selectedCourier = new Courier();
    }

    public void remove() {
        if (orderService.getOrdersByCourierId(selectedCourier.getId()).size() > 0) {
            errorMessage("This courier has orders, cannot delete.");
        } else {
            courierService.remove(selectedCourier);
            load();
            initNewEntity();
            infoMessage("Courier removed successfully.");

        }
    }

    public Courier getSelectedCourier() {
        return selectedCourier;
    }

    public void setSelectedCourier(Courier selectedCourier) {
        this.selectedCourier = selectedCourier;
    }

    public List<Courier> getList() {
        return list;
    }

}
