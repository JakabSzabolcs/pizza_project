package org.example.mbean.admin;

import org.example.entity.Courier;
import org.example.service.CourierService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class CourierMBean implements Serializable {
    private List<Courier> list = new ArrayList<>();
    private Courier selectedCourier = new Courier();

    @Inject
    private CourierService courierService;

    @PostConstruct
    private void init() {
        load();
    }

    private void load() {
        list = courierService.getAll();
    }

    public void save() {
        if (selectedCourier.getId() == null) {
            courierService.add(selectedCourier);
        } else {
            courierService.update(selectedCourier);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save: " + selectedCourier.getFirstName()+" " + selectedCourier.getLastName()));
        load();
        initNewEntity();
    }

    public void initNewEntity() {
        selectedCourier = new Courier();
    }

    public void remove() {
        courierService.remove(selectedCourier);
        load();
        initNewEntity();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful remove"));
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
