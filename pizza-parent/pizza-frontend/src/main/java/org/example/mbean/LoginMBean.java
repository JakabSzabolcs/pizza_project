package org.example.mbean;

import org.example.entity.Order;
import org.example.entity.User;
import org.example.entity.UserRole;
import org.example.mbean.admin.OrderMBean;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;


@ViewScoped
@Named
public class LoginMBean implements Serializable {

    @Inject
    private UserService userService;

    private User loggedInUser;

    private String username;
    private String password;


    @PostConstruct
    private void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        User adminUser = (User) session.getAttribute("admin");
        User regularUser = (User) session.getAttribute("user");
        if (adminUser != null) {
            loggedInUser = adminUser;
        } else if (regularUser != null) {
            loggedInUser = regularUser;
        }
    }

    public void login(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userService.findByUsername(username);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String redirectUrl = "";
            FacesMessage message = null;

            if (user.getRole().equals(UserRole.ADMIN)) {
                session.setAttribute("admin", user);
                redirectUrl = "admin/adminUsers.xhtml";
            } else {
                session.setAttribute("user", user);
                redirectUrl = "user/orderPizza.xhtml";
            }
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            errorMessage("Wrong username or password");
        }

    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("xhtml/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void errorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
    }

    public void infoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
    }

}
