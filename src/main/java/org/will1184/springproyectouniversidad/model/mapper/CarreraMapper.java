package org.will1184.springproyectouniversidad.model.mapper;

import org.will1184.springproyectouniversidad.model.dto.CarreraDTO;
import org.will1184.springproyectouniversidad.model.entity.Carrera;

@Deprecated
public class CarreraMapper {
    public static CarreraDTO mapCarrera(Carrera carrera){
        CarreraDTO dto= new CarreraDTO();
        dto.setCodigo(carrera.getId());
        dto.setNombre(carrera.getNombre());
        dto.setCantidad_anios(carrera.getCantidadAnios());
        dto.setCantidad_materias(carrera.getCantidadMaterias());
        return dto;
    }

}
