package org.will1184.springproyectouniversidad.service.contratos;

import org.springframework.data.jpa.repository.Query;
import org.will1184.springproyectouniversidad.model.entity.Aula;
import org.will1184.springproyectouniversidad.model.enums.Pizarron;

import java.util.Optional;

public interface AulaDAO extends GenericoDAO<Aula> {
    Iterable<Aula>findAulasByPizarron(Pizarron pizarron);

    Iterable<Aula>findAulasByPabellonNombre(String nombre);

    Optional<Aula> findAulaByNroAula(Integer nroAula);
}
