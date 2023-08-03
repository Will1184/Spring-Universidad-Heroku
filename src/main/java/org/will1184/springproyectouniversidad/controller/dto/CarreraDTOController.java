package org.will1184.springproyectouniversidad.controller.dto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.will1184.springproyectouniversidad.model.dto.AlumnoDTO;
import org.will1184.springproyectouniversidad.model.dto.CarreraDTO;
import org.will1184.springproyectouniversidad.model.entity.Carrera;
import org.will1184.springproyectouniversidad.model.entity.Persona;
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
public class CarreraDTOController {

    @Autowired
    private CarreraDAO carreraDAO;

    @Autowired
    CarreraMapperMs mapper;

    @GetMapping
    public ResponseEntity<?> findAllCarreras() {
        Map<String,Object> mensaje= new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) carreraDAO.findAll();
        List<CarreraDTO> dtos = carreras
                .stream()
                .map(mapper::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCarreraById(@PathVariable Integer id) {
        Map<String,Object> mensaje= new HashMap<>();
        Optional<Carrera> carreras =  carreraDAO.findById(id);
        Carrera carrera=carreras.get();
        CarreraDTO dtos = mapper.mapCarrera(carrera);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @PostMapping
    public ResponseEntity<?> altaCarrera( @RequestBody CarreraDTO carreraDTO ) {
        Map<String,Object> mensaje = new HashMap<>();

        Carrera carrera = mapper.mapCarrera(carreraDTO);
        carreraDAO.save(carrera);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data","");
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }
}
