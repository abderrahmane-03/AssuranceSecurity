package com.controller;

import com.entity.CarInsurance;
import com.entity.HealthInsurance;
import com.entity.HomeInsurance;
import com.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/insurances")
public class RequestInsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping("/RequestedHomeInsurance")
    public ModelAndView createHomeInsurance(
            @RequestParam double assetValue,
            @RequestParam String assetType,
            @RequestParam String localization,
            @RequestParam String securitySystem) {
        HomeInsurance homeInsurance = new HomeInsurance();
        homeInsurance.setAssetValue(assetValue);
        homeInsurance.setAssetType(assetType);
        homeInsurance.setLocalization(localization);
        homeInsurance.setSecuritySystem(securitySystem);
        int devisId = insuranceService.saveHomeInsurance(homeInsurance);

        return new ModelAndView("redirect:/devis?id=" + devisId);

    }

    @PostMapping("/RequestedCarInsurance")
    public ModelAndView createCarInsurance(@RequestParam int age,
                                           @RequestParam String vehicleType,
                                           @RequestParam String vehicleUsing,
                                           @RequestParam String driverHistory) {
        CarInsurance carInsurance = new CarInsurance();
        carInsurance.setAge(age);
        carInsurance.setVehicleType(vehicleType);
        carInsurance.setVehicleUsing(vehicleUsing);
        carInsurance.setDriverHistory(driverHistory);
        int devisId = insuranceService.saveCarInsurance(carInsurance);

        return new ModelAndView("redirect:/devis?id=" + devisId);

    }

    @PostMapping("/RequestedHealthInsurance")
    public ModelAndView createHealthInsurance(@RequestParam int age,
                                              @RequestParam String typeCoverage,
                                              @RequestParam String healthStatus) {
        HealthInsurance healthInsurance = new HealthInsurance();

        // Set the properties
        healthInsurance.setAge(age);
        healthInsurance.setTypeCoverage(typeCoverage);
        healthInsurance.setHealthStatus(healthStatus);


        int devisId = insuranceService.saveHealthInsurance(healthInsurance);

        return new ModelAndView("redirect:/devis?id=" + devisId);
    }


    @GetMapping("/InsuranceForm")
    public ModelAndView showRequestForm() {
        return new ModelAndView("InsuranceForm");
    }


    @GetMapping("/GetAllInsurances")
    public ModelAndView showInsurances() {
        return new ModelAndView("Insurances");
    }



}
