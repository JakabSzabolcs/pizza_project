package org.example.entity;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends CoreEntity{

    @NotNull
    @Column(name = "created_at")
    private Date createdAt;

    @NotNull
    @Size(max = 200)
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    //enum role
    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "creatorUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

}
