package com.controller;

import com.entity.Devis;
import com.entity.Subscription;
import com.repository.InsuranceRepository;
import com.repository.SubscriptionRepository;
import com.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class ViewsController {
    private final InsuranceRepository insuranceRepository;
    @Autowired
    SubscriptionRepository subscriptionRepository;
    public ViewsController(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("register");
    }
    @GetMapping("/loginForm")
    public ModelAndView showLoginForm() {
        return new ModelAndView("login");
    }
    @GetMapping("/")
    public ModelAndView insuranceHome() {
       return new ModelAndView("insurance");
    }
    @GetMapping("/devis")
    public ModelAndView showDevis(@RequestParam(required = false) Integer id) {
        ModelAndView modelAndView = new ModelAndView("devis");
        if (id != null) {
            Devis devis = insuranceRepository.findById(id); // Fetch the Devis by ID
            modelAndView.addObject("devis", devis);  // Add the Devis object to the model
        }

        return modelAndView;
    }
    @GetMapping("/subscription")
    public ModelAndView showSubscription() {
        ModelAndView modelAndView = new ModelAndView("subscription");

        // Fetch all subscriptions with Devis and insurance information for the logged-in client
        List<Subscription> subscriptions = subscriptionRepository.findAllByLoggedInClientWithInsurance();

        // Add the list of subscriptions to the model
        modelAndView.addObject("subscriptions", subscriptions);

        return modelAndView;
    }
}

