package com.service;

import com.entity.Devis;
import com.entity.HealthInsurance;
import com.entity.CarInsurance;
import com.entity.HomeInsurance;
import com.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Assurance.enums.Status;
import java.util.Optional;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    private double calculateCarInsurancePremium(CarInsurance carInsurance) {
        double premiumBase = 500; // Base premium
        if (carInsurance.getAge() < 25) premiumBase += premiumBase * 0.10;
        if ("luxe".equalsIgnoreCase(carInsurance.getVehicleType())) premiumBase += premiumBase * 0.15;
        if ("professionnelle".equalsIgnoreCase(carInsurance.getVehicleUsing())) premiumBase += premiumBase * 0.10;
        if ("no accidents".equalsIgnoreCase(carInsurance.getDriverHistory())) premiumBase -= premiumBase * 0.20;
        else premiumBase += premiumBase * 0.10;
        return premiumBase;
    }

    // Home Insurance Premium Calculation
    private double calculateHomeInsurancePremium(HomeInsurance homeInsurance) {
        double premiumBase = 300; // Base premium
        if ("maison".equalsIgnoreCase(homeInsurance.getAssetType())) premiumBase += premiumBase * 0.02;
        if ("zone à risque".equalsIgnoreCase(homeInsurance.getLocalization())) premiumBase += premiumBase * 0.05;
        if (homeInsurance.getAssetValue() > 200000) premiumBase += premiumBase * 0.10;
        if (homeInsurance.IsSecuritySystem()) premiumBase -= premiumBase * 0.15;
        else premiumBase += premiumBase * 0.15;
        return premiumBase;
    }

    private double calculateHealthInsurancePremium(HealthInsurance healthInsurance) {
        double premiumBase = 150; // Base premium
        if (healthInsurance.getAge() > 60) premiumBase += premiumBase * 0.20;
        if ("chronic illness".equalsIgnoreCase(healthInsurance.getHealthStatus())) premiumBase += premiumBase * 0.30;
        if ("basic".equalsIgnoreCase(healthInsurance.getTypeCoverage())) premiumBase -= premiumBase * 0.10;
        else if ("premium".equalsIgnoreCase(healthInsurance.getTypeCoverage())) premiumBase += premiumBase * 0.05;
        return premiumBase;
    }

    public int saveHomeInsurance(HomeInsurance homeInsurance) {
        Devis devis = new Devis();
        devis.setPremiumBase(300);
        devis.setPremiumTotal(calculateHomeInsurancePremium(homeInsurance));
        devis.setStatus(Status.PENDING);
        homeInsurance.setDevis(devis);
        devis.setHomeInsurance(homeInsurance);
        insuranceRepository.save(devis);
        insuranceRepository.save(homeInsurance);
        return devis.getId();
    }

    public int saveCarInsurance(CarInsurance carInsurance) {
        Devis devis = new Devis();
        devis.setPremiumBase(500);
        devis.setPremiumTotal(calculateCarInsurancePremium(carInsurance));
        devis.setStatus(Status.PENDING);
        devis.setCarInsurance(carInsurance);
        carInsurance.setDevis(devis);
        insuranceRepository.save(devis);
        insuranceRepository.save(carInsurance);
        return devis.getId();
    }


    // Save Health Insurance and Generate Devis
    public int saveHealthInsurance(HealthInsurance healthInsurance) {
        Devis devis = new Devis();
        devis.setPremiumBase(150);
        devis.setPremiumTotal(calculateHealthInsurancePremium(healthInsurance));
        devis.setStatus(Status.PENDING);
        devis.setHealthInsurance(healthInsurance);
        healthInsurance.setDevis(devis);
        insuranceRepository.save(healthInsurance);
        return devis.getId();
    }


//
//    public Optional<HealthInsurance> findHealthInsuranceById(Long id) {
//        return insuranceRepository.findById(HealthInsurance.class, id);
//    }
//
//    public Optional<CarInsurance> findCarInsuranceById(Long id) {
//        return insuranceRepository.findById(CarInsurance.class, id);
//    }
//
//    public Optional<HomeInsurance> findHomeInsuranceById(Long id) {
//        return insuranceRepository.findById(HomeInsurance.class, id);
//    }

    public void updateInsurance(Object insurance) {
        insuranceRepository.update(insurance);
    }

    public void deleteInsurance(Object insurance) {
        insuranceRepository.delete(insurance);
    }
}
