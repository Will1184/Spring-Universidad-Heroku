package org.will1184.springproyectouniversidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.will1184.springproyectouniversidad.model.entity.Carrera;
import org.will1184.springproyectouniversidad.service.contratos.AlumnoDAO;
import org.will1184.springproyectouniversidad.service.implementaciones.AlumnoDAOImpl;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringProyectoUniversidadApplication extends SpringBootServletInitializer {

//    @Autowired
//    AlumnoDAOImpl service;
    public static void main(String[] args) {
        SpringApplication.run
                (SpringProyectoUniversidadApplication.class, args)
                .getBeanDefinitionNames();
//        int num=0;
//        for (String str : beanDefinitionNames) {
//            num++;
//            System.out.println("beans "+num+" "+str);
//        }
    }
//    @Bean
//    public CommandLineRunner runner(){
//        return args -> {
////            Direccion direccion = new Direccion("calle circunvalacion","123"
////                    ,"1263","","","San Sebastian");
////
////            Persona alumno = new Alumno(null,"Frank","Lopez","01234567",direccion);
////            Persona persona = service.save(alumno);
////            System.out.println(persona.toString());
////            List<Persona> alumnos =(List<Persona>) service.findAll();
////            alumnos.forEach(System.out::println);
//
//
//        };
//    }



}
