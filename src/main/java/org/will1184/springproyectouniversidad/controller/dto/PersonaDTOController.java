package org.will1184.springproyectouniversidad.controller.dto;

import org.will1184.springproyectouniversidad.model.dto.PersonaDTO;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Empleado;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.entity.Profesor;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.AlumnoMapper;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.EmpleadoMapper;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.ProfesorMapper;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

import java.util.*;


public class PersonaDTOController extends GenericDTOController<Persona, PersonaDAO>{

    protected AlumnoMapper alumnoMapper;
    protected EmpleadoMapper empleadoMapper;
    protected ProfesorMapper profesorMapper;

    public PersonaDTOController(PersonaDAO service, String nombre_entidad, AlumnoMapper alumnoMapper) {
        super(service, nombre_entidad);
        this.alumnoMapper = alumnoMapper;
    }
    public PersonaDTOController(PersonaDAO service, String nombre_entidad, EmpleadoMapper empleadoMapper) {
        super(service, nombre_entidad);
        this.empleadoMapper = empleadoMapper;
    }

    public PersonaDTOController(PersonaDAO service, String nombre_entidad, ProfesorMapper profesorMapper) {
        super(service, nombre_entidad);
        this.profesorMapper = profesorMapper;
    }

    public List<PersonaDTO> findAllPersonas(){

        List<Persona> personas = super.findAll();
        List<PersonaDTO> dtos = new ArrayList<>();

        personas.forEach(persona -> {
            if (persona instanceof Alumno && alumnoMapper != null) {
                dtos.add(alumnoMapper.mapAlumno((Alumno) persona));
            } else if (persona instanceof Profesor && profesorMapper != null) {
                dtos.add(profesorMapper.mapProfesor((Profesor) persona));
            } else if (persona instanceof Empleado && empleadoMapper != null) {
                dtos.add(empleadoMapper.mapEmpleado((Empleado) persona));
            }
        });

        return dtos;
    }

    public PersonaDTO findPersonaId(Integer id){

        Optional<Persona>optionalPersona = super.findId(id);
        Persona persona;
        PersonaDTO dto = null;

        if (optionalPersona == null || optionalPersona.isEmpty()) {
                return null;
        }else {
                persona = optionalPersona.get();
        }

        if (persona instanceof Alumno){
                dto = alumnoMapper.mapAlumno((Alumno) persona);
        }else if(persona instanceof Profesor){
                dto = profesorMapper.mapProfesor((Profesor) persona);
        }else  if (persona instanceof Empleado){
                dto = empleadoMapper.mapEmpleado((Empleado) persona);
        }

        return dto;
    }

    public PersonaDTO createPersona(Persona persona){

        Persona personaEntidad = super.createEntidad(persona);
        PersonaDTO dto = null;

        if (personaEntidad instanceof Alumno){
            dto =alumnoMapper.mapAlumno((Alumno) personaEntidad);
        }else if(personaEntidad instanceof Profesor){
            dto =profesorMapper.mapProfesor((Profesor) personaEntidad);

        }else  if (personaEntidad instanceof Empleado){
            dto =empleadoMapper.mapEmpleado((Empleado) personaEntidad);
        }

        return dto;
    }

    public void deletePersonaId(Integer id){
        super.deleteById(id);
    }

    public PersonaDTO findPersonaNombreApellido( String nombre,String apellido){

        Optional<Persona>optionalPersona =service.findNameLastName(nombre,apellido);
        Persona persona;
        PersonaDTO dto = null;

        if (optionalPersona == null) {
            return null;
        }else {
            persona=optionalPersona.get();
        }

        if (persona instanceof Alumno){
            dto =alumnoMapper.mapAlumno((Alumno) persona);

        }else if(persona instanceof Profesor){
            dto =profesorMapper.mapProfesor((Profesor) persona);

        }else  if (persona instanceof Empleado){
            dto =empleadoMapper.mapEmpleado((Empleado) persona);
        }

        return dto;
    }

    public PersonaDTO findPersonaDni(String dni){

        Optional<Persona>optionalPersona =service.findDni(dni);
        Persona persona;
        PersonaDTO dto = null;

        if (optionalPersona == null || optionalPersona.isEmpty()) {
            return null;
        }else {
           persona= optionalPersona.get();
        }
        if (persona instanceof Alumno){
            dto = alumnoMapper.mapAlumno((Alumno) persona);

        }else if(persona instanceof Profesor){
            dto =profesorMapper.mapProfesor((Profesor) persona);

        }else  if (persona instanceof Empleado){
            dto =empleadoMapper.mapEmpleado((Empleado) persona);
        }

        return dto;
    }

}
