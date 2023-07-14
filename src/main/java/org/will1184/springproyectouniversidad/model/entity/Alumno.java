package org.will1184.springproyectouniversidad.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.will1184.springproyectouniversidad.model.Persona;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Alumnos")

public class Alumno extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
