package org.will1184.springproyectouniversidad.controller;

import org.springframework.web.bind.annotation.*;
import org.will1184.springproyectouniversidad.exception.BadRequestException;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

import java.util.Optional;

public class PersonaController extends GenericController<Persona, PersonaDAO> {
    public PersonaController(PersonaDAO service) {
        super(service);
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
    @GetMapping("/persona-dni")
    public Persona buscarPorDni(@RequestParam String dni){
        Optional<Persona>optionalPersona =service.findDni(dni);
        if (!optionalPersona.isPresent()){
            throw new BadRequestException(String.format("No se encontro persona con DNI "
                    +"%s",dni));
        }
        return optionalPersona.get();
    }
}
