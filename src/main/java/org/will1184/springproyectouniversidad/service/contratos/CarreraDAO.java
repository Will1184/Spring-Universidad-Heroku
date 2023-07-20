package org.will1184.springproyectouniversidad.service.contratos;

import org.will1184.springproyectouniversidad.model.entity.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera> {

    Iterable<Carrera> findCarreraByNombreContains(String nombre);
    Iterable<Carrera> findCarreraByNombreContainsIgnoreCase(String nombre);
    Iterable<Carrera> findCarreraByCantidadAniosAfter(Integer cantidadAnios);
    Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre,
                                                               String apellido);
}
