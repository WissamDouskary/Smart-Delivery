package com.smartlogi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.smartlogi.model.Colis;
import com.smartlogi.service.ColisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ColisController implements Controller {
    private ColisService colisService;

    public void setColisService(ColisService colisService) {
        this.colisService = colisService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();

        String path = request.getRequestURI();
        String[] parts = path.split("/");

        Long id = null;
        String lastPart = parts[parts.length - 1];
        if (lastPart.matches("\\d+")) {
            id = Long.parseLong(lastPart);
        }

        if(method.equals("GET")){
            if(id == null){
            return list();
            }else {
                return findById(id);
            }
        } else if(method.equals("POST")){
            return save(request);
        }else if(method.equals("PUT")){
            return update(request, id);
        }

        return null;
    }

    public ModelAndView list(){
        List<Colis> colis = colisService.findAll();

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("Colis", colis);
        return mav;
    }

    public ModelAndView findById(Long id){
        Colis colis = colisService.findByIdColis(id);

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("Colis", colis);
        return mav;
    }

    public ModelAndView save(HttpServletRequest request) throws IOException {
        Colis colis = readJson(request);
        ModelAndView mav = new ModelAndView("jsonView");
        if(colisService.saveColis(colis)){
            return mav.addObject("message", "La colie enregistrer pour le livreur : "+colis.getLivreur().getId());
        }else{
            return mav.addObject("error", "ce livreur deja avait une colis!");
        }

    }

    public ModelAndView update(HttpServletRequest request, Long id) throws IOException {
        Colis c = readJson(request);
        ModelAndView mav = new ModelAndView("jsonView");
        if(colisService.modify(c, id)){
            return mav.addObject("message", "colis modifie avec succes!");
        }else{
            return mav.addObject("error", "error en modifie la colie!");
        }
    }

    public Colis readJson(HttpServletRequest request) throws IOException {
        StringBuilder br = new StringBuilder();
        try(BufferedReader reader = request.getReader()){
            String line;
            while((line = reader.readLine()) != null){
                br.append(line);
            }
        }

        String json = br.toString();

        if (json.isEmpty()) {
            throw new IllegalArgumentException("Request body is empty");
        }

        ObjectMapper mapper = new ObjectMapper();

        try{
            return mapper.readValue(json, Colis.class);
        }catch (MismatchedInputException e){
            throw new IllegalArgumentException("Invalid json format!");
        }
    }
}
