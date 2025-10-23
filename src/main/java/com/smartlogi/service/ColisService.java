package com.smartlogi.service;

import com.smartlogi.model.Colis;
import com.smartlogi.model.Livreur;
import com.smartlogi.repository.ColisRepository;
import com.smartlogi.repository.LivreurRepository;

import java.util.List;

public class ColisService {
    protected ColisRepository colisRepository;
    protected LivreurRepository livreurRepository;

    public ColisService(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    public void setColisRepository(ColisRepository colisRepository) {
        this.colisRepository = colisRepository;
    }

    public List<Colis> findAll() {
        return colisRepository.findAll();
    }

    public Colis findByIdColis(Long id) {
        return colisRepository.findById(id).orElse(null);
    }

    public boolean saveColis(Colis c) {
        if (c.getAdresse() == null || c.getDestinataire() == null || c.getLivreur() == null || c.getPoids() == 0 || c.getStatut() == null) {
            return false;
        }

        Long livreurId = c.getLivreur().getId();
        Livreur livreur = livreurRepository.findById(livreurId).orElse(null);

        for (Colis colis : findAll()) {
            if (colis.getLivreur() != null && colis.getLivreur().getId().equals(livreurId)) {
                return false;
            }
        }
        c.setLivreur(livreur);

        colisRepository.save(c);
        return true;
    }

    public boolean modify(Colis newColie, Long id) {
        Colis oldColis = findByIdColis(id);

        if (newColie.getStatut() == null) {
            newColie.setStatut(oldColis.getStatut());
        } else if (newColie.getPoids() == 0) {
            newColie.setPoids(oldColis.getPoids());
        } else if (newColie.getLivreur() == null) {
            newColie.setLivreur(oldColis.getLivreur());
        } else if (newColie.getDestinataire() == null) {
            newColie.setDestinataire(oldColis.getDestinataire());
        } else if (newColie.getAdresse() == null) {
            newColie.setAdresse(newColie.getAdresse());
        }

        colisRepository.save(newColie);
        return true;
    }

    public boolean delete(Colis c) {
        colisRepository.delete(c);
        return true;
    }
}
