package org.will1184.springproyectouniversidad.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.will1184.springproyectouniversidad.model.dto.ProfesorDTO;
import org.will1184.springproyectouniversidad.model.entity.Profesor;

@Mapper(componentModel = "spring",config = ProfesorMapperConfig.class)
public interface ProfesorMapper {
    ProfesorDTO mapProfesorDTO(Profesor profesor);
    Profesor mapProfesor(ProfesorDTO profesorDTO);
}
