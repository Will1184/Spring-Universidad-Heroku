package org.will1184.springproyectouniversidad.model.mapper.mapstruct;

import org.mapstruct.Mapper;
import org.will1184.springproyectouniversidad.model.dto.EmpleadoDTO;
import org.will1184.springproyectouniversidad.model.entity.Empleado;

@Mapper(componentModel = "spring",config = EmpleadoMappperConfig.class)
public  interface EmpleadoMapper {

    EmpleadoDTO mapEmpleado(Empleado empleado);
    Empleado mapEmpleado(EmpleadoDTO empleadoDTO);

}
