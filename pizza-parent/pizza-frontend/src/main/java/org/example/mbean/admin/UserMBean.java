package org.example.mbean.admin;

import org.example.entity.Order;
import org.example.entity.User;
import org.example.entity.UserRole;
import org.example.mbean.LoginMBean;
import org.example.service.OrderService;
import org.example.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UserMBean extends LoginMBean implements Serializable {
    private List<User> list = new ArrayList<>();
    private User selectedUser = new User();
    private Long selectedCourierId;
    private boolean isAdmin;

    @Inject
    private UserService userService;


    @PostConstruct
    private void init() {
        load();
    }

    private void load() {
        list = userService.getAll();
    }

    public List<User> getList() {
        return list;
    }

    public void save() {
        boolean usernameExists = userService.checkUsernameExists(selectedUser.getUsername());

        if (usernameExists) {
            errorMessage("Username already exists");
            return;
        }

        if (selectedUser.getId() == null) {
            if (isAdmin) {
                selectedUser.setRole(UserRole.ADMIN);
            } else {
                selectedUser.setRole(UserRole.USER);
            }
            userService.add(selectedUser);
        } else {
            userService.update(selectedUser);
        }
        load();
        initNewUser();
        infoMessage("User saved successfully");
    }

    public void initNewUser() {
        selectedUser = new User();
    }


    public void selectedUser(User user) {
        selectedUser = user;
    }

    public void remove() {
        userService.remove(selectedUser);
        load();
        initNewUser();
        infoMessage("User removed successfully");
    }


    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Long getSelectedCourierId() {
        return selectedCourierId;
    }

    public void setSelectedCourierId(Long selectedCourierId) {
        this.selectedCourierId = selectedCourierId;
    }





}

