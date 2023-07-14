package org.will1184.springproyectouniversidad.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.will1184.springproyectouniversidad.model.entity.Direccion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Persona implements Serializable {
    private Integer id;
    private String nombre;
    private  String apellido;
    private String dni;
    private LocalDate fechaALta;
    private LocalDate fechaUltimaModificacion;
    @Embedded
    private Direccion direccion;

    @Override
    public String toString() {
        return
                "id: " + id +
                ", nombre: " + nombre +
                ", apellido: " + apellido +
                ", dni: " + dni +
                ", fechaALta: " + fechaALta +
                ", fechaUltimaModificacion: " + fechaUltimaModificacion +
                ", direccion: " + direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(id, persona.id) && Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni);
    }
}
