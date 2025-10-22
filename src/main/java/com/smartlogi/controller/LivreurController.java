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
import java.io.IOException;
import java.util.List;

public class LivreurController implements Controller {
    private LivreurService livreurService;

    public void setLivreurService(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        String idParam = request.getParameter("id");

        if(idParam == null){
            ModelAndView mav = new ModelAndView("jsonView");
            mav.addObject("error", "tu perdu le param id sur votre url!");
            return mav;
        }

        if(method.equals("GET")){
            return list();
        }else if(method.equals("POST")){
            return save(request);
        }else if(method.equals("PUT")){
            return update(request, Long.parseLong(idParam));
        }else if(method.equals("DELETE")){
            return delete(Long.parseLong(idParam));
        }
        return null;
    }

    public ModelAndView list(){
        List<Livreur> livreurs = livreurService.findAll();
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("livreurs", livreurs);
        return mav;
    }

    public ModelAndView save(HttpServletRequest request) throws IOException {
        Livreur livreur = readJson(request);
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("message", livreurService.saveLivreur(livreur));
        return mav;
    }

    public ModelAndView update(HttpServletRequest request, Long id) throws IOException {
        if(id == null){
            return null;
        }

        Livreur livreur = readJson(request);
        livreur.setId(id);

        ModelAndView mav = new ModelAndView("jsonView");

        if(livreurService.updateLivreur(livreur, id)){
            mav.addObject("message", "Livreur modifier avec succes");
            mav.addObject("Livreur", livreur);
        }else{
            mav.addObject("message", "error en course de modifier livreur informations!");
        }
        return mav;
    }

    public Livreur readJson(HttpServletRequest request) throws IOException{
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String json = sb.toString();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Livreur.class);
    }

    public ModelAndView delete(Long id) throws IOException {
        ModelAndView modelAndView = new ModelAndView("jsonView");

        if(livreurService.findById(id) == null){
            modelAndView.addObject("error", "Aucune livreur avec id: "+id);
            return modelAndView;
        }

        if(livreurService.deleteLivreur(id)){
            modelAndView.addObject("message", "Livreur supprimer avec succes!");
            return modelAndView;
        }else{
            modelAndView.addObject("error", "error en course de supprission de livreur avec id : "+ id);
            return modelAndView;
        }
    }
}