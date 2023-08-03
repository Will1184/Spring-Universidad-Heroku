package org.will1184.springproyectouniversidad.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AlumnoDTO extends PersonaDTO {

    private CarreraDTO carrera;

}
