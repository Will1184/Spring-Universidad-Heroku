package org.will1184.springproyectouniversidad.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.will1184.springproyectouniversidad.model.dto.AlumnoDTO;
import org.will1184.springproyectouniversidad.model.entity.Alumno;

@Mapper(componentModel = "spring",config = AlumnoMapperConfig.class,uses = CarreraMapperMs.class)
public interface AlumnoMapper {

     AlumnoDTO mapAlumno(Alumno alumno);
     Alumno mapAlumno(AlumnoDTO alumnoDTO);

}
