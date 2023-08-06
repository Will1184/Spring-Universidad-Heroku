package org.will1184.springproyectouniversidad.controller.dto;

import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.will1184.springproyectouniversidad.model.dto.CarreraDTO;
import org.will1184.springproyectouniversidad.model.entity.Carrera;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.CarreraMapperMs;
import org.will1184.springproyectouniversidad.service.contratos.CarreraDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "true")
public class CarreraDTOController extends GenericDTOController<Carrera,CarreraDAO> {

    private final CarreraMapperMs carreraMapper;
    public CarreraDTOController(CarreraDAO service, CarreraMapperMs carreraMapper) {
        super(service, "Carrera");
        this.carreraMapper = carreraMapper;
    }

    @GetMapping
    public ResponseEntity<?> findAllCarreras() {
        Map<String,Object> mensaje= new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) super.findAll();
        List<CarreraDTO> dtos = carreras
                .stream()
                .map(carreraMapper::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCarreraById(@PathVariable Integer id) {
        Map<String,Object> mensaje= new HashMap<>();
        Optional<Carrera> optionalCarrera =  super.findId(id);
        Carrera carrera;
        CarreraDTO dto;
        if ( optionalCarrera== null || optionalCarrera.isEmpty()) {
            mensaje.put("succes", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }else {
            carrera = optionalCarrera.get();
        }
        dto = carreraMapper.mapCarrera(carrera);

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dto);
        return ResponseEntity.ok().body(mensaje);
    }

    @PostMapping
    public ResponseEntity<?> createCarrera( @Valid @RequestBody CarreraDTO carreraDTO,BindingResult result ) {
        Map<String,Object> mensaje = new HashMap<>();

        Carrera carrera = carreraMapper.mapCarrera(carreraDTO);
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        super.createEntidad(carrera);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data","");
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarrera(@PathVariable Integer id, @Valid @RequestBody CarreraDTO carreraDTO
            , BindingResult result){
        Map<String,Object> mensaje= new HashMap<>();
        Carrera carrera;
        CarreraDTO dto;
        Optional<Carrera> optionalCarrera = super.findId(id);

        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        if (optionalCarrera ==null || optionalCarrera.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("%s con di %d no existe",nombre_entidad,id));
            return ResponseEntity.badRequest().body(mensaje);
        }else {
            carrera = optionalCarrera.get();
        }
        dto = carreraMapper.mapCarrera(carrera);
        dto.setNombre(carreraDTO.getNombre());
        dto.setCantidad_anios(carreraDTO.getCantidad_anios());
        dto.setCantidad_materias(carreraDTO.getCantidad_materias());
        carrera = carreraMapper.mapCarrera(dto);

        mensaje.put("datos",service.save(carrera));
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrera(@PathVariable Integer id){

        Map<String,Object> mensaje = new HashMap<>();
        Optional<Carrera> optionalCarrera = super.findId(id);

        if(optionalCarrera == null || optionalCarrera.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje",  String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        super.deleteById(id);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",  String.format("Se borro %s con Id %d", nombre_entidad, id));
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @PostMapping("/find-carreras")
    public ResponseEntity<?> findCarreraByNombreContains(@RequestParam String carrera){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findCarreraByNombreContains(carrera);
        if (carreras.isEmpty()){
           mensaje.put("success",Boolean.TRUE);
           mensaje.put("mensaje", String.format("No existe %s : con nombre %s || El nombre de la carrera debe ser exacto ",nombre_entidad,carrera));
           return ResponseEntity.badRequest().body(mensaje);
        }
        List<CarreraDTO> dtos = carreras.stream()
               .map(carreraMapper::mapCarrera)
               .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @PostMapping("/find-carreras/ignorecase")
    public ResponseEntity<?> findCarreraByNombreContainsIgnoreCase(@RequestParam String carrera){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findCarreraByNombreContainsIgnoreCase(carrera);
        if (carreras.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existe %s : con nombre %s ",nombre_entidad,carrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<CarreraDTO> dtos = carreras.stream()
                .map(carreraMapper::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @PostMapping("/find-carreras/anios/{anios}")
    public ResponseEntity<?> findCarreraByCantidadAniosAfter(@PathVariable Integer anios){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findCarreraByCantidadAniosAfter(anios);
        if (carreras.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existe %s mayor a %d anios",nombre_entidad,anios));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<CarreraDTO> dtos = carreras.stream()
                .map(carreraMapper::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @GetMapping("profesor-carreras/{nombre}/{apellido}")
    public ResponseEntity<Map<String, Object>> findCarrerasProfesorNombreApellido(@PathVariable String nombre,
                                                                                  @PathVariable String apellido){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.buscarCarrerasPorProfesorNombreYApellido(nombre,apellido);
        if (carreras.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existe %s que sea impartida por el profesor: %s %s",nombre_entidad,nombre,apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<CarreraDTO> dtos = carreras.stream()
                .map(carreraMapper::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }


}
