package org.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pizza")
public class Pizza extends AbstractEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @Column(name = "price")
    private Integer price;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(name, pizza.name) &&
                Objects.equals(details, pizza.details) &&
                Objects.equals(price, pizza.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, details, price);
    }
}
