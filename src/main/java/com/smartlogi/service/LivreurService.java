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

    public String saveLivreur(Livreur l){
        for(Livreur livreur : findAll()){
            if(livreur.getNom().equals(l.getNom()) && livreur.getPrenom().equals(l.getPrenom())){
                return "Le nom et prenom du livreur est deja trouve";
            }
        }
        livreurRepository.save(l);
        return "La Livreur est ajoute avec succes!";
    }
}
