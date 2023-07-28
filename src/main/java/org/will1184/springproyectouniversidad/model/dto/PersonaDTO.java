package org.will1184.springproyectouniversidad.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.will1184.springproyectouniversidad.model.Direccion;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    @Pattern(regexp = "[0-9]")
    @Size(min = 1,max = 10)
    private String dni;
    private Direccion direccion;
}
