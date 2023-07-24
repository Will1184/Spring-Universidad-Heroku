package org.will1184.springproyectouniversidad.controller;

import org.springframework.web.bind.annotation.*;
import org.will1184.springproyectouniversidad.exception.BadRequestException;
import org.will1184.springproyectouniversidad.model.entity.Carrera;
import org.will1184.springproyectouniversidad.service.contratos.CarreraDAO;

import java.util.Optional;

@RestController
@RequestMapping("/carreras")
public class CarreraController extends GenericController<Carrera, CarreraDAO> {

    public CarreraController(CarreraDAO service) {
        super(service);
        nombreEntidad = "Carrera";
    }

    @GetMapping("/{codigo}")
    public Carrera obtenerPorId(@PathVariable(value = "codigo",required = false) Integer id){
        Optional<Carrera> optionalCarrera =service.findById(id);
        if (!optionalCarrera.isPresent()){
            throw new BadRequestException(String.format("La carrera con di %d no existe",id));
        }
        return optionalCarrera.get();
    }



    @PostMapping("/busca-carreras")
    public Iterable<Carrera> findCarreraByNombreContains(@RequestParam String carrera){
        return service.findCarreraByNombreContains(carrera);
    }

    @PostMapping("/busca-carreras/ignorecase")
    public Iterable<Carrera> findCarreraByNombreContainsIgnoreCase(@RequestParam String carrera){
        return service.findCarreraByNombreContainsIgnoreCase(carrera);
    }

    @PostMapping("/busca-carreras/{anios}")
    public Iterable<Carrera> findCarreraByCantidadAniosAfter(@PathVariable Integer anios){
            return service.findCarreraByCantidadAniosAfter(anios);
    }

    @PostMapping
    public Carrera altaEntidad(@RequestBody Carrera carrera){
        if (carrera.getCantidadAnios()<0){
            throw new BadRequestException("El campo de años no puede ser negativo");
        }
        if (carrera.getCantidadMaterias()<0){
            throw new BadRequestException("El campo de cantidad de materias no puede ser negativo");
        }
        return service.save(carrera);
    }
    @GetMapping("profesor-carreras/{nombre}/{apellido}")
    public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(@PathVariable String nombre,
                                                               @PathVariable String apellido){
        return service.buscarCarrerasPorProfesorNombreYApellido(nombre,apellido);
    }

    @PutMapping("/{id}")
    public Carrera actualizarCarrera(@PathVariable Integer id,@RequestBody Carrera carrera){
        Carrera carreraUpdate = null;
        Optional<Carrera> optionalCarrera = service.findById(id);
        if (!optionalCarrera.isPresent()){
            throw new BadRequestException(String.format("La carrera con di %d no existe",id));
        }
        if (carrera.getCantidadAnios()<0){
            throw new BadRequestException("El campo de años no puede ser negativo");
        }
        if (carrera.getCantidadMaterias()<0){
            throw new BadRequestException("El campo de cantidad de materias no puede ser negativo");
        }
        carreraUpdate=optionalCarrera.get();
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidadMaterias(carrera.getCantidadMaterias());
        return service.save(carreraUpdate);
    }

    @DeleteMapping("/{id}")
    public void eliminarPorId(@PathVariable Integer id){
        service.deleteById(id);
    }
}
