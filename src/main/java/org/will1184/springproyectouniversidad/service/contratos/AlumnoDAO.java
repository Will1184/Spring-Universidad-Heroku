package org.will1184.springproyectouniversidad.service.contratos;

import org.will1184.springproyectouniversidad.model.entity.Persona;

public interface AlumnoDAO extends PersonaDAO {
Iterable<Persona> buscarAlumnosPorCarrera(String carrera);
}
