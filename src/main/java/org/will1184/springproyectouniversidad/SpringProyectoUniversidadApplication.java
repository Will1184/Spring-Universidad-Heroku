package org.will1184.springproyectouniversidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProyectoUniversidadApplication {

    public static void main(String[] args) {
        String[]beanDefinitionNames = SpringApplication.run
                (SpringProyectoUniversidadApplication.class, args)
                .getBeanDefinitionNames();
        int num=0;
        for (String str : beanDefinitionNames) {
            num++;
            System.out.println("beans "+num+" "+str);
        }
    }

}
