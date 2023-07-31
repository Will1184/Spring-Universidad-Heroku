package org.will1184.springproyectouniversidad.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.will1184.springproyectouniversidad.model.Direccion;
import org.will1184.springproyectouniversidad.model.entity.Carrera;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AlumnoDTO extends PersonaDTO {

    private Carrera carrera;

    public AlumnoDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion, Carrera carrera) {
        super(id, nombre, apellido, dni, direccion);
        this.carrera = carrera;
    }
}
