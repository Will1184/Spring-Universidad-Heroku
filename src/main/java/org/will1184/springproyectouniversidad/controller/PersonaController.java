package org.will1184.springproyectouniversidad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.will1184.springproyectouniversidad.exception.BadRequestException;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

import java.util.Optional;

public class PersonaController extends GenericController<Persona, PersonaDAO> {
    public PersonaController(PersonaDAO service) {
        super(service);
    }
    @PostMapping
    public Persona altaPersona(@RequestBody Persona alumno){
        return service.save(alumno);
    }
    @GetMapping("/nombre-apellido")
    public Persona buscarPersonaPorNombreYApellido(@RequestParam String nombre,
                                                   @RequestParam String apellido){
        Optional<Persona>optionalPersona =service.findNameLastName(nombre,apellido);
        if (!optionalPersona.isPresent()){
            throw new BadRequestException(String.format("No se encontro persona con nombre "
                    +"%s y appelido %s",nombre,apellido));
            }
        return optionalPersona.get();
    }
}