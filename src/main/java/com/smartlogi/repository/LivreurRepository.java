package com.smartlogi.repository;

import com.smartlogi.model.Livreur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class LivreurRepository {

    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Livreur> findAll() {
        return entityManager.createQuery("SELECT l FROM Livreur l", Livreur.class)
                .getResultList();
    }
}