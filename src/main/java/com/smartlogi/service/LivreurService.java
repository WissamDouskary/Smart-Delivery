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

    public Livreur findById(Long id){
        return livreurRepository.findById(id);
    }

    public boolean updateLivreur(Livreur l, Long id){
        Livreur livreur = findById(id);

        if(livreur == null){
            return false;
        }

        for(Livreur liv : findAll()){
            if(liv.getNom().equals(l.getNom()) && liv.getPrenom().equals(l.getPrenom())){
                return false;
            }
        }

        if(l.getNom() == null){
            l.setNom(livreur.getNom());
        }
        if(l.getPrenom() == null){
            l.setPrenom(livreur.getPrenom());
        }
        if (l.getTelephone() == null){
            l.setTelephone(livreur.getTelephone());
        }
        if (l.getVehicule() == null){
            l.setVehicule(livreur.getVehicule());
        }

        livreur.setNom(l.getNom());
        livreur.setPrenom(l.getPrenom());
        livreur.setTelephone(l.getTelephone());
        livreur.setVehicule(l.getVehicule());

        return livreurRepository.update(livreur, id);
    }

    public boolean deleteLivreur(Long id){
        Livreur l = livreurRepository.findById(id);
        return livreurRepository.delete(l);
    }
}
