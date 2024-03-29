package com.parking.parkinglot.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.Collection;

@Entity
public class User {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    private String userName;

    @Basic
    @Column(unique = true,nullable = false,length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    private String email;
    private String password;
    @Email
    @Column(unique = true,nullable = false,length = 100)
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Collection<Car> cars;

    @OneToMany(mappedBy = "owner")
    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }




}
