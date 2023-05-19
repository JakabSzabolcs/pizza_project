package org.example.mbean;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jdk.jfr.Name;
import org.example.entity.User;
import org.example.entity.UserRole;
import org.example.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    private UserRole role;


    @PostConstruct
    private void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
        loggedInUser = (User) session.getAttribute("user"); // Adj hozzá egy megfelelő kulcsot, ha a user más néven van tárolva

    }
    public void login(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userService.findByUsername(username);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("user", user);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            String redirectUrl = "";
            FacesMessage message = null;

            if (user.getRole().equals(UserRole.ADMIN)) {
                redirectUrl = "xhtml/admin.xhtml";
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres ADMIN bejelentkezés!", null);
            } else {
                redirectUrl = "xhtml/user.xhtml";
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres USER bejelentkezés", null);
            }

            FacesContext.getCurrentInstance().addMessage(null, message);

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sikertelen bejelentkezés", null));
        }

    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
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

    public UserRole getRole() {
        return role;
    }

    private String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
