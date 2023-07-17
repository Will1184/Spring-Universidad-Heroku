package org.will1184.springproyectouniversidad.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.will1184.springproyectouniversidad.model.entity.Persona;

@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona,Integer> {
}
