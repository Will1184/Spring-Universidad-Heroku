package org.will1184.springproyectouniversidad.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.will1184.springproyectouniversidad.model.Direccion;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO extends PersonaDTO {
    private BigDecimal sueldo;
    private Set<CarreraDTO>carreras;


    public ProfesorDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion, Set<CarreraDTO> carreras) {
        super(id, nombre, apellido, dni, direccion);
        this.carreras = carreras;
    }
}
