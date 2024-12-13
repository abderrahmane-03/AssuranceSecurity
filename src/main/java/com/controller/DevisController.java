package com.controller;

import com.Assurance.enums.Status;
import com.entity.CarInsurance;
import com.entity.Devis;
import com.entity.HealthInsurance;
import com.entity.HomeInsurance;
import com.service.DevisService;
import com.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping("/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;

    @GetMapping("/approve")
    public ModelAndView approve(@RequestParam Long id) {
        Optional<Devis> optionalDevis = devisService.findById(id);

        if (optionalDevis.isPresent()) {
            Devis devis = optionalDevis.get();
            devis.setStatus(Status.DONE);
            devisService.updateDevis(devis);
        }

        // Redirect to the devis page with the newly created Devis ID
        return new ModelAndView("redirect:/subscription");
    }

    @GetMapping("/reject")
    public ModelAndView reject(@RequestParam Long id) {
        Optional<Devis> optionalDevis = devisService.findById(id);
        if (optionalDevis.isPresent()) {
            Devis devis = optionalDevis.get();
            devis.setStatus(Status.CANCELED);
            devisService.updateDevis(devis);
        }
        return new ModelAndView("redirect:/subscription");
    }
}

