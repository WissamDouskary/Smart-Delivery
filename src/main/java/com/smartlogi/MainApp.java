package com.smartlogi;

import com.smartlogi.config.AppConfig;
import com.smartlogi.model.Colis;
import com.smartlogi.model.Livreur;
import com.smartlogi.service.ColisService;
import com.smartlogi.service.LivreurService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        LivreurService livreurService = ac.getBean(LivreurService.class);
        ColisService colisService = ac.getBean(ColisService.class);

        Livreur l1 = new Livreur();
        l1.setNom("Ali");
        l1.setPrenom("El Amrani");
        l1.setVehicule("Peugeot");
        l1.setTelephone("0612345678");
        livreurService.save(l1);

        Colis c1 = new Colis();
        c1.setDestinataire("Karim");
        c1.setAdresse("Agadir");
        c1.setPoids(5.2);
        c1.setStatut("Préparation");
        c1.setLivreur(l1);
        colisService.save(c1);

        System.out.println("Colis enregistrés: " + colisService.findAll());
    }
}
