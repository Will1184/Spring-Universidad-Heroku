package org.will1184.springproyectouniversidad.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion implements Serializable {
    private Long id;
    private String calle;
    private  String numero;
    private String codigoPostal;
    private String depto;
    private String piso;
    private String localidad;

    @Override
    public String toString() {
        return "id: " + id +
                ", calle: " + calle +
                ", numero: " + numero +
                ", codigoPostal: " + codigoPostal +
                ", depto: " + depto +
                ", piso: " + piso +
                ", localidad: " + localidad;
    }
}
