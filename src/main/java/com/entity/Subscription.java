package com.entity;


import com.Assurance.enums.Status;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String documentsProvided;


    @Column(name = "subscription_date", nullable = false)
    private LocalDate subscriptionDate;


    @Enumerated(EnumType.STRING)
    private Status Status;

    @ManyToOne
    private Client client;

    @OneToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentsProvided() {
        return documentsProvided;
    }

    public void setDocumentsProvided(String documentsProvided) {
        this.documentsProvided = documentsProvided;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status Status) {
        this.Status = Status;
    }
}
