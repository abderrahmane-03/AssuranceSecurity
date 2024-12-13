package com.entity;
import jakarta.persistence.*;

@Entity

public class HomeInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double assetValue;
    private String assetType;
    private String localization;
    private String securitySystem;
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

    public double getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getSecuritySystem() {
        return securitySystem;
    }

    public void setSecuritySystem(String securitySystem) {
        this.securitySystem = securitySystem;
    }

    public Boolean IsSecuritySystem() {
        return Boolean.valueOf(securitySystem);
    }

}
