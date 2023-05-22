package org.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "courier")
public class Courier extends AbstractEntity {

    @NotNull
    @Size(max = 500)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(max = 500)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(max = 500)
    @Column(name = "phone_number")
    private String phoneNumber;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}