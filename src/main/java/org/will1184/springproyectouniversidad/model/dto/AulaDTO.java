package org.will1184.springproyectouniversidad.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.will1184.springproyectouniversidad.model.enums.Pizarron;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AulaDTO {

    private Integer id;
    @NotNull
    private Integer nroAula;
    private String medidas;
    private Integer cantPupitres;
    private Pizarron pizarron;
    private PabellonDTO pabellon;

}
