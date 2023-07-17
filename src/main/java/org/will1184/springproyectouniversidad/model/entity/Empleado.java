package org.will1184.springproyectouniversidad.model.entity;

import jakarta.persistence.*;
import lombok.*;

import org.will1184.springproyectouniversidad.model.enums.TipoEmpleado;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona {

    private BigDecimal sueldo;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_empleado")
    private TipoEmpleado tipoEmpleado;
    @OneToOne(
            optional = true,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "pabellon_id",
            foreignKey = @ForeignKey(name = "FK_PABELLON_ID")
    )
    private Pabellon pabellon;

    @Override
    public String toString() {
        return "\tEmpleado:" +
                "sueldo: " + sueldo +
                ", tipoEmpleado: " + tipoEmpleado +
                ", pabellon: " + pabellon;
    }
}
