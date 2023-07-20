package org.will1184.springproyectouniversidad.service.contratos;

import org.springframework.data.jpa.repository.Query;
import org.will1184.springproyectouniversidad.model.entity.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon> {
    Iterable<Pabellon> findAllPabellonByLocalidad(String localidad);
    Iterable<Pabellon> findAllPabellonByNombre(String nombre);
}
