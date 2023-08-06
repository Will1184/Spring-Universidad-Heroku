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
import org.will1184.springproyectouniversidad.model.dto.EmpleadoDTO;
import org.will1184.springproyectouniversidad.model.dto.PersonaDTO;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Empleado;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.enums.TipoEmpleado;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.EmpleadoMapper;
import org.will1184.springproyectouniversidad.service.contratos.EmpleadoDAO;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empleados")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "true")
@Tag(name = "Empleados", description = "Catálogo de empleados")
public class EmpleadoDTOController extends PersonaDTOController{

    public EmpleadoDTOController(@Qualifier("empleadoDAOImpl") PersonaDAO service, EmpleadoMapper empleadoMapper) {
        super(service, "empleado", empleadoMapper);
    }

    @Operation(summary = "Obtiene todos los registros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los registros de alumnos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @GetMapping
    public ResponseEntity<?> findAllEmpleados(){
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
    public ResponseEntity<?> findEmpleadoId(@PathVariable Integer id) {
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
            @ApiResponse(responseCode = "200", description = "Se creo el registro de alumno",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "422", description = "No se pudo crear el registro: Datos no validos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @PostMapping
    public ResponseEntity<?> createEmpleado(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();

        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }
        PersonaDTO save = super.createPersona(empleadoMapper.mapEmpleado((EmpleadoDTO) personaDTO));
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",save);
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
    public ResponseEntity<?> updateEmpleado(@PathVariable Integer id,
                                            @Valid @RequestBody EmpleadoDTO empleadoDTO, BindingResult result){

        Map<String,Object> mensaje = new HashMap<>();
        PersonaDTO personaDTO= super.findPersonaId(id);
        EmpleadoDTO dto;
        Empleado empleadoUpdate;

        if(personaDTO==null) {
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("%s con id %d no existe",nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        if (result.hasErrors()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("validaciones",super.obtenerValidaciones(result));
            return ResponseEntity.unprocessableEntity().body(mensaje);
        }
        dto = ((EmpleadoDTO)personaDTO);
        dto.setNombre(empleadoDTO.getNombre());
        dto.setApellido(empleadoDTO.getApellido());
        dto.setDireccion(empleadoDTO.getDireccion());
        dto.setDni(empleadoDTO.getDni());
        dto.setTipoEmpleado(empleadoDTO.getTipoEmpleado());
        dto.setSueldo(empleadoDTO.getSueldo());
        dto.setPabellon(empleadoDTO.getPabellon());

        empleadoUpdate = empleadoMapper.mapEmpleado(dto);
        mensaje.put("datos",super.createPersona(empleadoUpdate));
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
    public ResponseEntity<?> deleteEmpleadoId(@PathVariable Integer id){

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
    public ResponseEntity<?> findEmpleadoNombreApellido(@PathVariable String nombre, @PathVariable String apellido){

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
    @GetMapping("/empleado-dni")
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

    @Operation(summary = "Encuentra todos los empleados por tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se encontro los registros ",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
            @ApiResponse(responseCode = "400", description = "No existe registro",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alumno.class)))),
    })
    @GetMapping("/tipo-empleado")
    public ResponseEntity<?> findEmpleadosTipoEmpleado(@RequestBody TipoEmpleado tipoEmpleado){

        Map<String,Object> mensaje = new HashMap<>();
        List<Persona> empleados = ((List<Persona>)((EmpleadoDAO)service).buscarEmpleadosPorTipoEmpleado(tipoEmpleado));
        if (empleados.isEmpty()){
            mensaje.put("success",Boolean.TRUE);
            mensaje.put("mensaje", String.format("No existen empleado de tipo: %s ",tipoEmpleado));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<EmpleadoDTO> dtos = empleados.stream()
                .map(persona -> empleadoMapper.mapEmpleado((Empleado) persona))
                .collect(Collectors.toList());

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",dtos);
        return ResponseEntity.ok().body(mensaje);
    }

}
