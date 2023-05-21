package org.example.mbean;

import org.apache.bcel.generic.FLOAD;
import org.example.entity.Courier;
import org.example.service.CourierService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CourierMBean implements Serializable {

    private List<Courier> courierList = new ArrayList<>();
    private Courier selectedCourier = new Courier();
    private Courier newCourier;

    @Inject
    private CourierService courierService;

    @PostConstruct
    private void init() {
        courierList = courierService.getAll();
        newCourier = new Courier();
    }
    private void load() {
        courierList = courierService.getAll();
    }

    public void save() {
        if (selectedCourier == null) {
            selectedCourier.setCreationDate(newCourier.getCreationDate());
            courierService.add(newCourier);
        } else {

            courierService.update(selectedCourier);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successful save: " + selectedCourier.getFirstName()));
        load();
    }

    public void remove(Courier courier) {
        courierService.remove(courier);
        load();
        initNewCourier();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes", "Succesful delete"));
    }

    public void initNewCourier() {
        newCourier = new Courier();
    }

    public List<Courier> getCourierList() {
        return courierList;
    }

    public void setCourierList(List<Courier> courierList) {
        this.courierList = courierList;
    }

    public Courier getSelectedCourier() {
        return selectedCourier;
    }


}
