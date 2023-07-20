package org.will1184.springproyectouniversidad.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.entity.Profesor;

@Repository
public interface ProfesorRepository extends PersonaRepository{
    @Query("SELECT p FROM Profesor p JOIN p.carreras c WHERE c.nombre =?1")
    Iterable<Persona> buscarProfesoresPorCarrera(String carrera);
}
