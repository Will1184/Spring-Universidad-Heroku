package org.will1184.springproyectouniversidad.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.will1184.springproyectouniversidad.model.dto.CarreraDTO;
import org.will1184.springproyectouniversidad.model.entity.Carrera;

@Mapper(componentModel = "spring")
public interface CarreraMapperMs {
    @Mappings({
            @Mapping(source = "id",target = "codigo"),
            @Mapping(source = "nombre",target = "nombre"),
            @Mapping(source = "cantidadMaterias",target = "cantidad_materias"),
            @Mapping(source = "cantidadAnios",target = "cantidad_anios"),
    })
    CarreraDTO mapCarrera(Carrera carrera);
}
