package com.repository;

import com.entity.Client;
import com.entity.Subscription;
import com.service.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriptionRepository extends GenericRepository<Subscription, Long> {


    @Autowired
    private HttpSession session;

    @Autowired
    private ClientService clientService;

    /**
     * Retrieve all subscriptions with associated Devis and insurance details for the logged-in client.
     *
     * @return List of subscriptions with their Devis and insurance for the logged-in client
     */
    public List<Subscription> findAllByLoggedInClientWithInsurance() {
        // Get the client ID from session
        Long clientId = (Long) session.getAttribute("client_id");

        if (clientId == null) {
            throw new IllegalArgumentException("Client ID is required but not found in session.");
        }

        // Verify that the client exists
        Client client = clientService.findById(clientId).orElseThrow(() ->
                new IllegalArgumentException("Client with ID " + clientId + " not found."));

        // Query to fetch subscriptions and associated Devis and insurance for the logged-in client
        return entityManager.createQuery(
                        "SELECT s FROM Subscription s " +
                                "JOIN FETCH s.devis d " +
                                "LEFT JOIN FETCH d.homeInsurance " +
                                "LEFT JOIN FETCH d.carInsurance " +
                                "LEFT JOIN FETCH d.healthInsurance " +
                                "WHERE s.client.id = :clientId", Subscription.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }
}
