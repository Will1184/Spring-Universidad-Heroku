package org.will1184.springproyectouniversidad.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.will1184.springproyectouniversidad.model.Direccion;
import org.will1184.springproyectouniversidad.model.entity.Pabellon;
import org.will1184.springproyectouniversidad.model.enums.TipoEmpleado;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO extends PersonaDTO{
    private BigDecimal sueldo;
    private TipoEmpleado tipoEmpleado;
    private Pabellon pabellon;

    public EmpleadoDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo, TipoEmpleado tipoEmpleado, Pabellon pabellon) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
        this.pabellon = pabellon;
    }
}
