package com.entity;
import jakarta.persistence.*;

@Entity

public class HealthInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String healthStatus;
    private String typeCoverage;

    @OneToOne(cascade = CascadeType.PERSIST) // Ensure the Devis is saved when HealthInsurance is saved
    @JoinColumn(name = "devis_id")
    private Devis devis;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getTypeCoverage() {
        return typeCoverage;
    }

    public void setTypeCoverage(String typeCoverage) {
        this.typeCoverage = typeCoverage;
    }
}
