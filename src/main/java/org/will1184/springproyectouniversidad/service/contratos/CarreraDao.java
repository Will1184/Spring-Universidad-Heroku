package org.will1184.springproyectouniversidad.service.contratos;

import org.will1184.springproyectouniversidad.model.entity.Carrera;

import java.util.Optional;

public interface CarreraDao {
    Optional<Carrera> findById(Integer id);
    Carrera save(Carrera carrera);
    Iterable<Carrera>findAll();
    void deleteById(Integer id);

}
