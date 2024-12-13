package com.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional

public class GenericRepository<T, ID> {
    @PersistenceContext
    protected EntityManager entityManager;

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public Optional<T> findById(Class<T> clazz, ID id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    public void delete(T entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            entityManager.remove(entityManager.merge(entity));
        }
    }
}



