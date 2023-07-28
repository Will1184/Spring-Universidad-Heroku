package org.will1184.springproyectouniversidad.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO {
    private Integer codigo;
    private String nombre;
    private Integer cantidad_materias;
    private Integer cantidad_anios;

}
