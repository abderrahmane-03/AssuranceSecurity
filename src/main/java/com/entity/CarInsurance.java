package com.entity;
import jakarta.persistence.*;

@Entity

public class CarInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String vehicleType;
    private String vehicleUsing;
    private String driverHistory;
    @OneToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleUsing() {
        return vehicleUsing;
    }

    public void setVehicleUsing(String vehicleUsing) {
        this.vehicleUsing = vehicleUsing;
    }

    public String getDriverHistory() {
        return driverHistory;
    }

    public void setDriverHistory(String driverHistory) {
        this.driverHistory = driverHistory;
    }

}
