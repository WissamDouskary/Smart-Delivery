package com.smartlogi.repository;

import com.smartlogi.model.Livreur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

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

    public void save(Livreur l){
        entityManager.persist(l);
    }

    public Livreur findById(Long id){
        return entityManager.find(Livreur.class, id);
    }

    public boolean update(Livreur l, Long id){
        entityManager.merge(l);
        return true;
    }
}