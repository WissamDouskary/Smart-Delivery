package com.smartlogi;

import com.smartlogi.config.AppConfig;
import com.smartlogi.service.LivreurService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        LivreurService service = context.getBean(LivreurService.class);
        System.out.println(service.findAll());
    }
}
