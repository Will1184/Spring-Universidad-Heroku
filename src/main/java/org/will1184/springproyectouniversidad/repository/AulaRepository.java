package org.will1184.springproyectouniversidad.repository;

import org.will1184.springproyectouniversidad.model.entity.Aula;
import org.will1184.springproyectouniversidad.model.enums.Pizarron;

import java.util.Optional;

public interface AulaRepository {
    Iterable<Aula>findAulasByPizarron(Pizarron pizarron);
    Iterable<Aula>findAulasByPabellonNombre(String nombre);
    Optional<Aula> findAulaByNroAula(Integer nroAula);
}
