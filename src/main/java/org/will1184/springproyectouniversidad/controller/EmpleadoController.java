package org.will1184.springproyectouniversidad.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.will1184.springproyectouniversidad.exception.BadRequestException;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.enums.TipoEmpleado;
import org.will1184.springproyectouniversidad.service.contratos.EmpleadoDAO;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

import java.util.Optional;

@RestController
@RequestMapping("/Empleados")
public class EmpleadoController  extends PersonaController{


    public EmpleadoController(@Qualifier("empleadoDAOImpl") PersonaDAO service) {
        super(service);
        nombreEntidad = "Empleado";
    }

    @GetMapping("/tipo-empleado")
    public Iterable<Persona> buscarEmpleadosPorTipoEmpleado(@RequestBody TipoEmpleado tipoEmpleado){
        return ((EmpleadoDAO)service).buscarEmpleadosPorTipoEmpleado(tipoEmpleado);
    }

    @PutMapping("/{id}")
    public Persona actualizarEmpleado(@PathVariable Integer id, @RequestBody Persona empleado){
        Persona empleadoUpdate;
        Optional<Persona> oEmpleado = service.findById(id);
        if(!oEmpleado.isPresent()) {
            throw new BadRequestException(String.format("Empleado con id %d no existe", id));
        }
        empleadoUpdate = oEmpleado.get();
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellido(empleado.getApellido());
        empleadoUpdate.setDireccion(empleado.getDireccion());
        return service.save(empleadoUpdate);
    }
}