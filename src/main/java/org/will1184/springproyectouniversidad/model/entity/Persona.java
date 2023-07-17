package org.will1184.springproyectouniversidad.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 60)
    private String nombre;
    @Column(nullable = false,length = 60)
    private  String apellido;
    @Column(nullable = false,unique = true,length = 10)
    private String dni;
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "depto", column = @Column(name = "departamento"))
    })
    private Direccion direccion;

    @PrePersist
    public void antesDePersistir(){
        this.fechaAlta=LocalDate.now();
    }

    @PreUpdate
    public void antesDeUpdate(){
        this.fechaModificacion=LocalDate.now();
    }

    @Override
    public String toString() {
        return
                "id: " + id +
                ", nombre: " + nombre +
                ", apellido: " + apellido +
                ", dni: " + dni +
                ", fechaALta: " + fechaAlta +
                ", fechaUltimaModificacion: " + fechaModificacion;
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
