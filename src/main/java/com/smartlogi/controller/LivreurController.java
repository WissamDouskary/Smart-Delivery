package com.smartlogi.controller;

import com.smartlogi.model.Livreur;
import com.smartlogi.service.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/livreurs")
public class LivreurController{
    private LivreurService livreurService;

    @Autowired
    public void setLivreurService(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @GetMapping
    public List<Livreur> getAllLivreurs(){
        return livreurService.findAll();
    }
}
