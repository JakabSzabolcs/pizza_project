package org.example.mbean;

import org.example.entity.UserRole;
import org.example.service.UserService;


import java.io.Serializable;



public class LoginMBean implements Serializable{

    private UserService userService;

    private String username;
    private String password;

    private UserRole role;

    public UserRole getRole() {
        return role;
    }

/*
    public String login(String username,String password) {
        if (userService.findByUsername(username) != null && userService.findByUsername(username).getPassword().equals(password)) {
            if (userService.findByUsername(username).getRole().equals(UserRole.ADMIN)) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", userService.findByUsername(username));
                return "admin.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", userService.findByUsername(username));
                return "user.xhtml?faces-redirect=true";
            }

        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("Login failed", true);
        return "login.xhtml?faces-redirect=true";
    }
*/

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
}
