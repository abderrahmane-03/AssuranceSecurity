package com.repository;

import com.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InsuranceRepository extends GenericRepository<Object, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    public Devis findById(int id) {

        return entityManager.createQuery("SELECT c FROM Devis c WHERE c.id = :id", Devis.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
