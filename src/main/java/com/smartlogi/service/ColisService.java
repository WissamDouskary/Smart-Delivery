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
        c.setLivreur(livreur);

        colisRepository.save(c);
        return true;
    }

    public boolean modify(Colis newColie, Long id) {
        Colis oldColis = findByIdColis(id);
        if (oldColis == null) return false;

        if (newColie.getAdresse() != null)
            oldColis.setAdresse(newColie.getAdresse());
        if (newColie.getDestinataire() != null)
            oldColis.setDestinataire(newColie.getDestinataire());
        if (newColie.getLivreur() != null)
            oldColis.setLivreur(newColie.getLivreur());
        if (newColie.getPoids() != 0)
            oldColis.setPoids(newColie.getPoids());
        if (newColie.getStatut() != null)
            oldColis.setStatut(newColie.getStatut());

        colisRepository.save(oldColis);
        return true;
    }

    public boolean delete(Colis c) {
        colisRepository.delete(c);
        return true;
    }
}
