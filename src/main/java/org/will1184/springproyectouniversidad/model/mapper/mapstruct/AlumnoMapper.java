package org.will1184.springproyectouniversidad.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.will1184.springproyectouniversidad.model.dto.AlumnoDTO;
import org.will1184.springproyectouniversidad.model.dto.PersonaDTO;
import org.will1184.springproyectouniversidad.model.entity.Alumno;


@Mapper(componentModel = "spring",config = AlumnoMapperConfig.class,uses = CarreraMapperMs.class)
public interface AlumnoMapper {
     AlumnoDTO mapAlumnoDTO(Alumno alumno);
     AlumnoDTO mapAlumnoDTO(PersonaDTO personaDTO);
     Alumno mapAlumno(AlumnoDTO alumnoDTO);
     Alumno mapAlumno(PersonaDTO personaDTO);

}
