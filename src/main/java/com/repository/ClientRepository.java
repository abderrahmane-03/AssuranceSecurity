package com.repository;

import com.entity.Client;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class ClientRepository extends GenericRepository<Client,Long>{

    @PersistenceContext
    private EntityManager entityManager;

    public Client findByEmail(String email) {

           return entityManager.createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class)
                    .setParameter("email", email)
                    .getSingleResult();


    }

}
