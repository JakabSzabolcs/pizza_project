package org.example.mbean;

import org.example.entity.User;
import org.example.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserMBean implements Serializable {

}
