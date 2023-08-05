package org.will1184.springproyectouniversidad.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.will1184.springproyectouniversidad.model.dto.AulaDTO;
import org.will1184.springproyectouniversidad.model.dto.PabellonDTO;
import org.will1184.springproyectouniversidad.model.entity.Aula;
import org.will1184.springproyectouniversidad.model.entity.Pabellon;

@Mapper(componentModel = "spring")
public interface PabellonMapper {
    PabellonDTO mapPabellon(Pabellon pabellon);
    Pabellon mapPabellon(PabellonDTO pabellon);
    @Mapping(target = "pabellon", ignore = true)
    @Mapping(target = "medidas", ignore = true)
    @Mapping(target = "pizarron", ignore = true)
    @Mapping(target = "cantPupitres", ignore = true)
    AulaDTO mapAula(Aula aula);

}
