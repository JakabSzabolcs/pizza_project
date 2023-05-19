package org.example.mbean;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jdk.jfr.Name;
import org.example.entity.UserRole;
import org.example.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@ViewScoped
@Named
public class LoginMBean implements Serializable{

    @Inject
    private UserService userService;

    private String username;
    private String password;

    private UserRole role;

    public UserRole getRole() {
        return role;
    }



    public String login(String username,String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (userService.findByUsername(username) != null && passwordEncoder.matches(password, userService.findByUsername(username).getPassword())) {
            if (userService.findByUsername(username).getRole().equals(UserRole.ADMIN)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres ADMIN bejelentkezés!", null));
                return "/xhtml/admin.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres USER bejelentkezés", null));
                return "/xhtml/user.xhtml?faces-redirect=true";
            }

        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sikertelen bejelentkezés", null));
        return null;
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
    private String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
}
