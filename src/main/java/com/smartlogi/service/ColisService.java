package com.smartlogi.service;

import com.smartlogi.model.Colis;
import com.smartlogi.repository.ColisRepository;

import java.util.List;

public class ColisService {
    protected ColisRepository colisRepository;

    public void setColisRepository(ColisRepository colisRepository) {
        this.colisRepository = colisRepository;
    }

    public List<Colis> findAll(){return colisRepository.findAll();}
}
