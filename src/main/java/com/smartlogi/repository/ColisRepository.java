package com.smartlogi.repository;

import com.smartlogi.model.Colis;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ColisRepository {
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Colis> findAll(){
        return entityManager.createQuery("FROM Colis ", Colis.class).getResultList();
    }
}
