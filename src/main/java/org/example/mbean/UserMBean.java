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
    @Inject
    private UserService userService;

    private List<User> userList;
    private User selectedUser;
    private boolean editMode;

    @PostConstruct
    public void init() {
        userList = userService.getAll();
        selectedUser = new User();
        editMode = false;
    }

    public List<User> getUserList() {
        return userList;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public void addUser() {
        userService.add(selectedUser);
        userList.add(selectedUser);
        selectedUser = new User();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User added successfully.", null));
    }

    public void updateUser() {
        userService.update(selectedUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User updated successfully.", null));
        exitEditMode();
    }

    public void deleteUser() {
        userService.remove(selectedUser);
        userList.remove(selectedUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User deleted successfully.", null));
        exitEditMode();
    }

    public void enterEditMode() {
        editMode = true;
    }

    public void exitEditMode() {
        editMode = false;
        selectedUser = new User();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
