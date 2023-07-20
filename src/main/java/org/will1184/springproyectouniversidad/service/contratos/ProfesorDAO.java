package org.will1184.springproyectouniversidad.service.contratos;

import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.entity.Profesor;

public interface ProfesorDAO extends PersonaDAO {
    Iterable<Persona> buscarProfesoresPorCarrera(String carrera);
}
