package org.will1184.springproyectouniversidad.controller.dto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.will1184.springproyectouniversidad.model.dto.PersonaDTO;
import org.will1184.springproyectouniversidad.model.dto.ProfesorDTO;
import org.will1184.springproyectouniversidad.model.entity.*;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.CarreraMapperMs;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.ProfesorMapper;
import org.will1184.springproyectouniversidad.service.contratos.CarreraDAO;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;
import org.will1184.springproyectouniversidad.service.contratos.ProfesorDAO;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profesores")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "true")
public class ProfesorDTOController extends PersonaDTOController{

    private final CarreraDAO carreraDAO;
    private final CarreraMapperMs carreraMapperMs;

    public ProfesorDTOController(@Qualifier("profesorDAOImpl") PersonaDAO service, ProfesorMapper profesorMapper, CarreraDAO carreraDAO, CarreraMapperMs carreraMapperMs) {
        super(service, "profesor", profesorMapper);
        this.carreraDAO = carreraDAO;
        this.carreraMapperMs = carreraMapperMs;
    }

    @GetMapping
    public ResponseEntity<?> findAllProfesores(){
        Map<String, Object> mensaje = new HashMap<>();
        List<PersonaDTO> dtos = super.findAllPersonas();
        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProfesorId(@PathVariable Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        PersonaDTO dto = super.findPersonaId(id);
        if (dto == null) {
            mensaje.put("succes", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", dto);
        return ResponseEntity.ok().body(mensaje);
    }

    @PostMapping
    public ResponseEntity<?> createProfesor(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        PersonaDTO save = super.createPersona(profesorMapper.mapProfesor((ProfesorDTO) personaDTO));
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",save);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfesor(@PathVariable Integer id,
                                            @Valid @RequestBody ProfesorDTO profesorDTO, BindingResult result){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO= super.findPersonaId(id);
        ProfesorDTO dto;
        Profesor profesorUpdate;

        if(personaDTO==null) {
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("%s con id %d no existe",nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        dto = ((ProfesorDTO)personaDTO);
        dto.setNombre(profesorDTO.getNombre());
        dto.setApellido(profesorDTO.getApellido());
        dto.setDireccion(profesorDTO.getDireccion());
        dto.setDni(profesorDTO.getDni());
        dto.setSueldo(profesorDTO.getSueldo());

        profesorUpdate = profesorMapper.mapProfesor(dto);
        mensaje.put("datos",super.createPersona(profesorUpdate));
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfesorId(@PathVariable Integer id){
        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO = super.findPersonaId(id);
        if(personaDTO==null) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje",  String.format("No existe %s con Id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        super.deletePersonaId(id);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",  String.format("Se borro %s con Id %d", nombre_entidad, id));
        return ResponseEntity.status(HttpStatus.OK).body(mensaje);
    }

    @GetMapping("/nombre-apellido/{nombre}/{apellido}")
    public ResponseEntity<?> findProfesorNombreApellido(
            @PathVariable String nombre, @PathVariable String apellido){
        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO = super.findPersonaNombreApellido(
                nombre,apellido);
        if (personaDTO==null){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("No se encontro persona con nombre +%s y appelido %s",nombre,apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos",personaDTO);
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @GetMapping("/profesor-dni")
    public ResponseEntity<Map<String, Object>> findProfesorDni(@RequestParam String dni){
        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO dto = super.findPersonaDni(dni);
        if (dto == null){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro %s con DNI: %s",nombre_entidad,dni));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos",dto);
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @GetMapping("/profesores-carreras")
    public ResponseEntity<?> findProfesoresCarrera(@RequestBody String carrera){

        Map<String,Object> mensaje = new HashMap<>();
        List<Persona> personas = ((List<Persona>)((ProfesorDAO)service).buscarProfesoresPorCarrera(carrera));
        List<ProfesorDTO> dtos = personas.stream()
                .map(persona -> profesorMapper.mapProfesor((Profesor) persona))
                .collect(Collectors.toList());

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @PutMapping("/{idProfesor}/carrera/{idCarrera}")
    public ResponseEntity<?> assignCarreraProfesor(@PathVariable Integer idProfesor, @PathVariable Integer idCarrera){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO = super.findPersonaId(idProfesor);
        Optional<Carrera> oCarrera;
        Profesor profesor;
        Carrera carrera;
        Set<Carrera> carreras;

        if(personaDTO==null) {
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("%s con id %d no existe",nombre_entidad, idProfesor));
            return ResponseEntity.badRequest().body(mensaje);
        }

        oCarrera = carreraDAO.findById(idCarrera);
        if(oCarrera.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("Carrera con id %d no existe",idCarrera ));
            return ResponseEntity.badRequest().body(mensaje);
        }
        profesor = profesorMapper.mapProfesor((ProfesorDTO) personaDTO);
        carrera = oCarrera.get();
        carreras = new HashSet<>();
        carreras.add(carrera);
        profesor.setCarreras(carreras);

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",service.save(profesor));
        return ResponseEntity.ok().body(mensaje);
    }

}

