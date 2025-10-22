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

        String path = request.getRequestURI();
        String[] parts = path.split("/");
        Long id = Long.parseLong(parts[parts.length - 1]);

        if (id == null) {
            ModelAndView mav = new ModelAndView("jsonView");
            mav.addObject("error", "tu perdu le param id sur votre url!");
            return mav;
        }

        if (method.equals("GET")) {
            return list();
        } else if (method.equals("POST")) {
            return save(request);
        } else if (method.equals("PUT")) {
            return update(request, id);
        } else if (method.equals("DELETE")) {
            return delete(id);
        }
        return null;
    }

    public ModelAndView list() {
        List<Livreur> livreurs = livreurService.findAll();
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("livreurs", livreurs);
        return mav;
    }

    public ModelAndView save(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView("jsonView");
        try {
            Livreur livreur = readJson(request);
            mav.addObject("message", livreurService.saveLivreur(livreur));
        } catch (IllegalArgumentException e) {
            mav.addObject("error", e.getMessage());
        } catch (IOException e) {
            mav.addObject("error", "Erreur lors de la lecture du corps de la requête.");
        }
        return mav;
    }

    public ModelAndView update(HttpServletRequest request, Long id) throws IOException {
        ModelAndView mav = new ModelAndView("jsonView");

        if (id == null) {
            mav.addObject("error", "ID manquant pour la mise à jour.");
            return mav;
        }

        try {
            Livreur livreur = readJson(request);
            livreur.setId(id);

            if (livreurService.updateLivreur(livreur, id)) {
                mav.addObject("message", "Livreur modifié avec succès");
                mav.addObject("livreur", livreur);
            } else {
                mav.addObject("error", "Erreur lors de la mise à jour du livreur.");
            }
        } catch (IllegalArgumentException e) {
            mav.addObject("error", e.getMessage());
        } catch (IOException e) {
            mav.addObject("error", "Erreur lors de la lecture du corps de la requête.");
        }

        return mav;
    }

    public ModelAndView delete(Long id) throws IOException {
        ModelAndView modelAndView = new ModelAndView("jsonView");

        if (livreurService.findById(id) == null) {
            modelAndView.addObject("error", "Aucune livreur avec id: " + id);
            return modelAndView;
        }

        if (livreurService.deleteLivreur(id)) {
            modelAndView.addObject("message", "Livreur supprimer avec succes!");
            return modelAndView;
        } else {
            modelAndView.addObject("error", "error en course de supprission de livreur avec id : " + id);
            return modelAndView;
        }
    }

    public Livreur readJson(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String json = sb.toString();

        if (json.isEmpty()) {
            throw new IllegalArgumentException("Request body is empty");
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, Livreur.class);
        } catch (com.fasterxml.jackson.databind.exc.MismatchedInputException e) {
            throw new IllegalArgumentException("Invalid JSON format");
        }
    }

}