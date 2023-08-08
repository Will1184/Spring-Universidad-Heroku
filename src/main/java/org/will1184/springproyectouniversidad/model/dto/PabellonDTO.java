package org.will1184.springproyectouniversidad.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.will1184.springproyectouniversidad.model.Direccion;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PabellonDTO {

    private Integer id;
    @NotNull
    @NotEmpty
    private String nombre;
    private Double mts2;
    private Set<AulaDTO> aulas;
    private Direccion direccion;
}
