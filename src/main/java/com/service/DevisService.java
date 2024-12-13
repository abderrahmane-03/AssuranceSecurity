package com.service;

import com.Assurance.enums.Status;
import com.entity.Client;
import com.entity.Devis;
import com.entity.Subscription;
import com.repository.DevisRepository;
import com.repository.SubscriptionRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


    @Service
    public class DevisService {

        @Autowired
        private DevisRepository devisRepository;
        @Autowired
        private SubscriptionRepository subscriptionRepository;
        @Autowired
        private ClientService clientService;
        @Autowired
        private HttpSession session;


        public Optional<Devis> findById(Long id) {
            return devisRepository.findById(Devis.class, id);
        }

        public int updateDevis(Devis devis) {
            devisRepository.update(devis);

            int subscriptionId = 0;

            if (devis.getStatus() == Status.DONE) {
                Long clientId = (Long) session.getAttribute("client_id");

                if (clientId == null) {
                    throw new IllegalArgumentException("Client ID is required but not found in session.");
                }

                Optional<Client> optionalClient = clientService.findById(clientId);

                if (optionalClient.isPresent()) {
                    Client client = optionalClient.get();
                    Subscription subscription = new Subscription();
                    subscription.setClient(client);
                    subscription.setDevis(devis);
                    subscription.setSubscriptionDate(LocalDate.now());
                    subscription.setDocumentsProvided("ggg.png");
                    subscription.setStatus(Status.PENDING);

                    subscriptionRepository.save(subscription);
                    subscriptionId = subscription.getId(); // Assign the saved subscription ID
                }
            }
            return subscriptionId;
        }


        public void deleteDevis(Devis devis) {
            devisRepository.delete(devis);
        }
    }

