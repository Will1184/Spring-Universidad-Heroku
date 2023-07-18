package org.will1184.springproyectouniversidad.service.contratos;

import org.springframework.data.repository.CrudRepository;
import org.will1184.springproyectouniversidad.model.entity.Persona;

import java.util.Optional;

public interface PersonaDAO extends GenericoDAO<Persona> {
    Optional<Persona> findNameLastName(String nombre,String apellido);
    Optional<Persona> findDni(String dni);
    Iterable<Persona> findLastName(String apellido);
}
