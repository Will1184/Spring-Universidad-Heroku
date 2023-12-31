package org.will1184.springproyectouniversidad.controller.dto;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.will1184.springproyectouniversidad.service.contratos.GenericoDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class GenericDTOController <E,S extends GenericoDAO<E>> {
    protected final S service;
    protected  final String nombre_entidad;

    public List<E> findAll(){
        return  (List<E>) service.findAll();
    }
    public Optional<E> findId(Integer id){
        return (Optional<E>) service.findById(id);
    }

    public E createEntidad(E entidad){
        return service.save(entidad);
    }

    protected Map<String,Object> obtenerValidaciones(BindingResult result){
        Map<String,Object> validaciones = new HashMap<>();
        result.getFieldErrors()
                .forEach(error-> validaciones.put(error.getField(),error.getDefaultMessage()));
        return validaciones;
    }

    public void deleteById(Integer id){
        service.deleteById(id);
    }

}
