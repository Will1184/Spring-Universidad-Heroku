package org.will1184.springproyectouniversidad.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.will1184.springproyectouniversidad.model.entity.Carrera;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera,Integer> {
}
