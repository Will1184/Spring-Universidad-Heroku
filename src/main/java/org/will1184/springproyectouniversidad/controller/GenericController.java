package org.will1184.springproyectouniversidad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.will1184.springproyectouniversidad.exception.BadRequestException;
import org.will1184.springproyectouniversidad.service.contratos.GenericoDAO;

import java.util.List;

public class GenericController<E,S extends GenericoDAO<E>> {
    protected final S service;
    protected String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }
    @GetMapping
    public List<E> obtenerTodos(){
        List<E>list=(List<E>)service.findAll();
        if (list.isEmpty()){
            throw new BadRequestException(String.format("No se han encontrado %ss",nombreEntidad));
        }
        return list;
    }
}
