package com.smartlogi;

import com.smartlogi.service.LivreurService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");

        System.out.println("Beans count : "+context.getBeanDefinitionCount());
    }
}
