package org.will1184.springproyectouniversidad.service.contratos;

import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Persona;

public interface AlumnoDAO extends PersonaDAO {
//    Iterable<Aula>findAulasByPizarron(Pizarron pizarron);
//    Iterable<Aula>findAulasByPabellonNombre(String nombre);
//    Optional<Aula> findAulaByNroAula(Integer nroAula);
Iterable<Persona> buscarAlumnosPorCarrera(String carrera);
}
