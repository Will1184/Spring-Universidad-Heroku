package org.will1184.springproyectouniversidad.controller;

import org.springframework.web.bind.annotation.*;
import org.will1184.springproyectouniversidad.exception.BadRequestException;
import org.will1184.springproyectouniversidad.service.contratos.GenericoDAO;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public E altaEntidad(@RequestBody E entidad){
        return service.save(entidad);
    }

    @GetMapping("/{id}")
    public E obtenerPorId(@PathVariable(required = false) Integer id){
        Optional<E> oEntidad = service.findById(id);
        if(!oEntidad.isPresent()) {
            throw new BadRequestException(String.format("Entidad con id %d no existe", id));
        }
        return oEntidad.get();
    }


    @DeleteMapping("/{id}")
    public void eliminarPorId(@PathVariable Integer id){
        Optional<E> oEntidad = service.findById(id);
        if(!oEntidad.isPresent()) {
            throw new BadRequestException(String.format("Entidad con id %d no existe", id));
        }
        service.deleteById(id);
    }
}
