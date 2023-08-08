package org.will1184.springproyectouniversidad.service.contratos;

import java.util.Optional;

public interface GenericoDAO<E>{
    Optional<E> findById(Integer id);
    E save(E entidad);
    Iterable<E>findAll();
    void deleteById(Integer id);
}
