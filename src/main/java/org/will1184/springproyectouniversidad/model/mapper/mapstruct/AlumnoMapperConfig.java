package org.will1184.springproyectouniversidad.model.mapper.mapstruct;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.will1184.springproyectouniversidad.model.dto.AlumnoDTO;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
@MapperConfig
public interface AlumnoMapperConfig extends PersonaMapperConfig{
    @InheritConfiguration(name = "mapPersona")
    void mapAlumno(Alumno alumno, @MappingTarget AlumnoDTO alumnoDTO);

}
