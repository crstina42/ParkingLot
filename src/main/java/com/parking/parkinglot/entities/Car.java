package com.parking.parkinglot.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Basic
    private String licensePlate;
  @Size(min=3,max=100)
  @Column(unique=true,nullable = false,length = 100)
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Basic
    private String parkingSpot;

    @Size(min=3,max=100)
    @Column(unique=true,nullable = false,length = 100)

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    @ManyToOne
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    private CarPhoto photo;

    @OneToOne(mappedBy="car", cascade =CascadeType.ALL, fetch=FetchType.LAZY )
    public CarPhoto getPhoto(){
        System.out.println("esti un pui");return photo;}
    public void setPhoto(CarPhoto photo){
        this.photo=photo;
    }

}
