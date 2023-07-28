package org.will1184.springproyectouniversidad.model.mapper.mapstruct;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.will1184.springproyectouniversidad.model.dto.PersonaDTO;
import org.will1184.springproyectouniversidad.model.entity.Persona;

@MapperConfig
public interface PersonaMapperConfig {
    void mapPersona(Persona persona,@MappingTarget PersonaDTO PersonaDTO);
}
