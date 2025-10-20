package com.smartlogi.service;

import com.smartlogi.model.Livreur;
import com.smartlogi.repository.LivreurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreurService {
    protected LivreurRepository livreurRepository;

    @Autowired
    public LivreurService(LivreurRepository livreurRepository){
        this.livreurRepository = livreurRepository;
    }

    public List<Livreur> findAll(){return livreurRepository.findAll();}
    public Livreur save(Livreur l){return livreurRepository.save(l);}
    public void delete(Livreur l){livreurRepository.delete(l);}
}
