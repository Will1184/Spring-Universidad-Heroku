package org.will1184.springproyectouniversidad.controller.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.will1184.springproyectouniversidad.model.dto.AlumnoDTO;
import org.will1184.springproyectouniversidad.model.dto.PersonaDTO;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Carrera;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.AlumnoMapper;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.CarreraMapperMs;
import org.will1184.springproyectouniversidad.service.contratos.AlumnoDAO;
import org.will1184.springproyectouniversidad.service.contratos.CarreraDAO;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "true")
@Tag(name = "Alumnos", description = "Catálogo de alumnos")
public class AlumnoDTOController extends PersonaDTOController {

    private final CarreraDAO carreraDAO;
    private final CarreraMapperMs carreraMapperMs;

    public AlumnoDTOController(@Qualifier("alumnoDAOImpl") PersonaDAO service, AlumnoMapper alumnoMapper, CarreraDAO carreraDAO, CarreraMapperMs carreraMapperMs) {
        super(service, "alumno", alumnoMapper);
        this.carreraDAO = carreraDAO;
        this.carreraMapperMs = carreraMapperMs;
    }

    @Operation(summary = "Obtiene todos los registros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "todos los registros de alumnos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @GetMapping
    public ResponseEntity<?> findAllAlumnos(){
        Map<String, Object> mensaje = new HashMap<>();
        List<PersonaDTO> dtos = super.findAllPersonas();
        mensaje.put("succes", Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Obtiene el registro por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro obtenido",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "400", description = "No existe registro con id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findAlumnoId(@PathVariable Integer id) {
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

    @Operation(summary = "Crea un registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "se creo el registro de alumno",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "422", description = "No se pudo crear el registro: Datos no validos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @PostMapping
    public ResponseEntity<?> createAlumno(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();

        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }
        Persona persona = alumnoMapper.mapAlumno((AlumnoDTO) personaDTO);

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",super.createPersona(persona));
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @Operation(summary = "Modifica un registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifico el registro ",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "422", description = "No se pudo crear el registro: Datos no validos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "400", description = "No existe registro con id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAlumno(@PathVariable Integer id,
                                             @Valid @RequestBody AlumnoDTO alumnoDTO,BindingResult result){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO= super.findPersonaId(id);
        AlumnoDTO dto;
        Alumno alumnoUpdate;

        if(personaDTO == null) {
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("%s con id %d no existe",nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }

        dto = ((AlumnoDTO)personaDTO);
        dto.setNombre(alumnoDTO.getNombre());
        dto.setApellido(alumnoDTO.getApellido());
        dto.setDireccion(alumnoDTO.getDireccion());
        dto.setDni(alumnoDTO.getDni());

        alumnoUpdate = alumnoMapper.mapAlumno(dto);
        mensaje.put("datos",super.createPersona(alumnoUpdate));
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Borra un registro ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se borro el registro ",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "400", description = "No existe registro con id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlumnoId(@PathVariable Integer id){

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

    @Operation(summary = "Encuentra un registro por nombre y apellido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se encontro el registro ",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "400", description = "No existe registro con id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @GetMapping("/nombre-apellido/{nombre}/{apellido}")
    public ResponseEntity<?> findAlumnoNombreApellido(
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

    @Operation(summary = "Encuentra un registro por DNI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se encontro el registro ",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "400", description = "No existe registro con DNI",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @GetMapping("/alumno-dni")
    public ResponseEntity<Map<String, Object>> findAlumnoDni(@RequestParam String dni){

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

    @Operation(summary = "Asigna una carrera a alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se asigno carrera ",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "400", description = "No existe registro con id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public ResponseEntity<?> assignCarreraAlumno(@PathVariable Integer idAlumno, @PathVariable Integer idCarrera){

        Map<String,Object> mensaje= new HashMap<>();
        PersonaDTO oAlumno = super.findPersonaId(idAlumno);
        Alumno alumno;
        Carrera carrera;
        Optional<Carrera> oCarrera;

        if(oAlumno==null) {
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("Alumno con id %d no existe", idAlumno));
            return ResponseEntity.badRequest().body(mensaje);
        }
        oCarrera= carreraDAO.findById(idCarrera);

        if(oCarrera.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("Carrera con id %d no existe",idCarrera ));
            return ResponseEntity.badRequest().body(mensaje);
        }
        alumno = alumnoMapper.mapAlumno((AlumnoDTO) oAlumno);
        carrera = oCarrera.get();
        alumno.setCarrera(carrera);

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",service.save(alumno));
        return ResponseEntity.ok().body(mensaje);
    }

    @Operation(summary = "Encuentra todos los alumnos de una carrera")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se encontro los registros ",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "400", description = "No existe registro",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @GetMapping("/alumnos-carrera/{carrera}")
    public ResponseEntity<?> findAlumnosCarrera(@PathVariable String carrera){

        Map<String,Object> mensaje= new HashMap<>();
        List<Persona> alumnos = ((List<Persona>)((AlumnoDAO)service).buscarAlumnosPorCarrera(carrera));
        if (alumnos.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existen alumnos en carrera: %s ",carrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<AlumnoDTO> dtos =alumnos.stream()
                        .map(persona -> alumnoMapper.mapAlumno((Alumno) persona))
                        .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return  ResponseEntity.ok().body(mensaje);
    }

}
