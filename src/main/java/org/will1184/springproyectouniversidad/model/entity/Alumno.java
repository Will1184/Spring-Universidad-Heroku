package org.will1184.springproyectouniversidad.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.will1184.springproyectouniversidad.model.Direccion;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alumnos")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Alumno extends Persona {
    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "carreras_id")
    private Carrera carrera;

    public Alumno(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        super(id, nombre, apellido, dni, direccion);
    }

    public Alumno(Integer id, String nombre, String apellido, String dni, Direccion direccion, Carrera carrera) {
        super(id, nombre, apellido, dni, direccion);
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return super.toString()+" Alumno{}";
    }
}
