package org.example.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delivery_date")
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_pizza",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    private List<Pizza> pizzas = new ArrayList<>();

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "street_type")
    private String streetType;

    @NotNull
    @Column(name = "house_number")
    private String houseNumber;




    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
