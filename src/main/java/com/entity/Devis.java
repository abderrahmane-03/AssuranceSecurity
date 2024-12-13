package com.entity;

import jakarta.persistence.*;
import com.Assurance.enums.Status;
@Entity
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double premiumBase;
    private double premiumTotal;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "devis")
    @JoinColumn(name="healthInsurance_id")
    private HealthInsurance healthInsurance;

    @OneToOne(mappedBy = "devis")
    @JoinColumn(name="carInsurance_id")
    private CarInsurance carInsurance;

    @OneToOne(mappedBy = "devis")
    @JoinColumn(name="homeInsurance_id")
    private HomeInsurance homeInsurance;

    @OneToOne(mappedBy = "devis")
    @JoinColumn(name="subscription_id")
    private Subscription subscription;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public double getPremiumBase() {
        return premiumBase;
    }

    public void setPremiumBase(double premiumBase) {
        this.premiumBase = premiumBase;
    }

    public double getPremiumTotal() {
        return premiumTotal;
    }

    public void setPremiumTotal(double premiumTotal) {
        this.premiumTotal = premiumTotal;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CarInsurance getCarInsurance() {
        return carInsurance;
    }

    public void setCarInsurance(CarInsurance carInsurance) {
        this.carInsurance = carInsurance;
    }

    public HealthInsurance getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(HealthInsurance healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public HomeInsurance getHomeInsurance() {
        return homeInsurance;
    }

    public void setHomeInsurance(HomeInsurance homeInsurance) {
        this.homeInsurance = homeInsurance;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }


}
