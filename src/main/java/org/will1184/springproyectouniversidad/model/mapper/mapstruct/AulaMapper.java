package org.will1184.springproyectouniversidad.model.mapper.mapstruct;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.will1184.springproyectouniversidad.model.dto.AulaDTO;
import org.will1184.springproyectouniversidad.model.dto.PabellonDTO;
import org.will1184.springproyectouniversidad.model.entity.Aula;
import org.will1184.springproyectouniversidad.model.entity.Pabellon;

import java.util.LinkedHashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface AulaMapper {
    AulaDTO mapAula(Aula aula);
    Aula mapAula(AulaDTO aula);
    @Mapping(target = "mts2", ignore = true)
    @Mapping(target = "aulas", ignore = true)
    PabellonDTO pabellonToPabellonDTO(Pabellon pabellon);
    
}
