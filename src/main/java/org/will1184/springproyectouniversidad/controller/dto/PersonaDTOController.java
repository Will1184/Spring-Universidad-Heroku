package org.will1184.springproyectouniversidad.controller.dto;

import org.will1184.springproyectouniversidad.model.dto.PersonaDTO;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Empleado;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.entity.Profesor;
import org.will1184.springproyectouniversidad.model.mapper.mapstruct.AlumnoMapper;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

import java.util.Optional;

public class PersonaDTOController extends GenericDTOController<Persona, PersonaDAO>{
    protected final AlumnoMapper alumnoMapper;
    public PersonaDTOController(PersonaDAO service, String nombre_entidad, AlumnoMapper alumnoMapper) {
        super(service, nombre_entidad);
        this.alumnoMapper = alumnoMapper;
    }


    public PersonaDTO altaPersona(Persona persona){
        Persona personaEntidad =super.altaEntidad(persona);
        PersonaDTO dto = null;

        if (personaEntidad instanceof Alumno){
            dto =alumnoMapper.mapAlumno((Alumno) personaEntidad);

        }else if(personaEntidad instanceof Profesor){

        }else  if (personaEntidad instanceof Empleado){

        }
        return dto;
    }

    public PersonaDTO findPersonaId(Integer id){
        Optional<Persona>optionalPersona= super.findId(id);
        Persona persona;
        PersonaDTO dto = null;
        if (optionalPersona.isEmpty()){
            return null;
        }else {
            persona = optionalPersona.get();
        }
        if (persona instanceof Alumno){
            dto = alumnoMapper.mapAlumno((Alumno) persona);
        }else if(persona instanceof Profesor){

        }else  if (persona instanceof Empleado){

        }
        return dto;
    }

}
