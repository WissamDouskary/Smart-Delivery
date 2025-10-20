package com.smartlogi.service;

import com.smartlogi.model.Colis;
import com.smartlogi.repository.ColisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColisService {
    @Autowired
    protected ColisRepository colisRepository;

    public List<Colis> findAll(){return colisRepository.findAll();}
    public Colis save(Colis c){return colisRepository.save(c);}
    public void delete(Colis c){colisRepository.delete(c);}
}
