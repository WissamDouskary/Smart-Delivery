package com.smartlogi.controller;

import com.smartlogi.model.Colis;
import com.smartlogi.service.ColisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.List;

public class ColisController implements Controller {
    private ColisService colisService;

    public void setColisService(ColisService colisService) {
        this.colisService = colisService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        if(method.equals("GET")){
            return list();
        }
        return null;
    }

    public ModelAndView list(){
        List<Colis> colis = colisService.findAll();

        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("Colis", colis);
        return mav;
    }
}
