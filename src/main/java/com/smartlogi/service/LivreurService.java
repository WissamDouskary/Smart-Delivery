package com.smartlogi.service;

import com.smartlogi.model.Livreur;
import com.smartlogi.repository.LivreurRepository;

import java.util.List;


public class LivreurService {
    private LivreurRepository livreurRepository;

    public void setLivreurRepository(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    public List<Livreur> findAll() {
        return livreurRepository.findAll();
    }
}
