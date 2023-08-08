package org.will1184.springproyectouniversidad;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringProyectoUniversidadApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run
                (SpringProyectoUniversidadApplication.class, args)
                .getBeanDefinitionNames();
    }
}
