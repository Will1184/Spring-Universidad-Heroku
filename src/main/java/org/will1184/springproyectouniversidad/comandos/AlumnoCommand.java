package org.will1184.springproyectouniversidad.comandos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.service.contratos.AlumnoDAO;
import org.will1184.springproyectouniversidad.service.contratos.CarreraDAO;

import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

import java.util.Optional;

@Component
@Order(4)
public class AlumnoCommand implements CommandLineRunner {
    @Autowired
    @Qualifier("alumnoDAOImpl")
    private PersonaDAO personaDAO;
    @Autowired
    private CarreraDAO carreraDAO;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------- ************* Alumnos Command ********** --------");
        System.out.println("----Busqueda por apellido");
        Iterable<Persona> iPersona = personaDAO.findLastName(ObjetosDummy.getAlumnoDos().getApellido());
        iPersona.forEach(System.out::println);
        System.out.println("---Busqueda de persona por DNI");
        Optional<Persona> optionalPersona = personaDAO.findDni(ObjetosDummy.getEmpleadoUno().getDni());
        optionalPersona.ifPresent(persona -> System.out.println(persona.toString()));
        System.out.println("----Busqueda de persona por nombre y apellido");
        optionalPersona = personaDAO.findNameLastName(ObjetosDummy.getProfesorUno().getNombre(),ObjetosDummy.getProfesorDos().getApellido());
        optionalPersona.ifPresent(persona -> System.out.println(persona.toString()));
        System.out.println("----- Busqueda de alumnos por carrera ----");
        Iterable<Alumno> iAlumnos = ((AlumnoDAO)personaDAO).findAlumnosByNombreCarrera(ObjetosDummy.getCarreraIngSis().getNombre());
        iAlumnos.forEach(System.out::println);

    }
}