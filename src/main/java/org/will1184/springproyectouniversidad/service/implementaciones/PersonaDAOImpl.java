package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.repository.PersonaRepository;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

import java.util.Optional;

public class PersonaDAOImpl extends GenericoDAOImpl<Persona, PersonaRepository> implements PersonaDAO {

    public PersonaDAOImpl(PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findNameLastName(String nombre, String apellido) {
        return repository.buscarPorNombreYApellido(nombre,apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findDni(String dni) {
        return repository.buscarPorDni(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findLastName(String apellido) {
        return repository.buscarPersonaPorApellido(apellido);
    }
}
