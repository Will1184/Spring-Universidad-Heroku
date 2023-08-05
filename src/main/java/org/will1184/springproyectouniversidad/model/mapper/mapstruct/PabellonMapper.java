package org.will1184.springproyectouniversidad.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.will1184.springproyectouniversidad.model.dto.PabellonDTO;
import org.will1184.springproyectouniversidad.model.entity.Pabellon;

@Mapper(componentModel = "spring")
public interface PabellonMapper {
    PabellonDTO mapPabellon(Pabellon pabellon);
    Pabellon mapPabellon(PabellonDTO pabellon);
}
