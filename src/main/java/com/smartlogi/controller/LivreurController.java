package com.smartlogi.controller;

import com.smartlogi.model.Livreur;
import com.smartlogi.service.LivreurService;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.util.List;

public class LivreurController implements Controller {
    private LivreurService livreurService;

    public void setLivreurService(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        if(method.equals("GET")){
            return list();
        }else if(method.equals("POST")){

            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = request.getReader()) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }

            String json = sb.toString();

            ObjectMapper mapper = new ObjectMapper();
            Livreur livreur = mapper.readValue(json, Livreur.class);

            ModelAndView mav = new ModelAndView("jsonView");
            mav.addObject("message", livreurService.saveLivreur(livreur));
            return mav;
        }
        return null;
    }

    public ModelAndView list(){
        List<Livreur> livreurs = livreurService.findAll();
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("livreurs", livreurs);
        return mav;
    }

}