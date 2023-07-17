package org.will1184.springproyectouniversidad.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.will1184.springproyectouniversidad.model.enums.Pizarron;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aulas")
public class Aula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "numero_aula",nullable = false)
    private Integer nroAula;
    @Column(name = "medidas_mtsxmts",nullable = false)
    private String medidas;
    @Column(name = "cantidad_pupitres")
    private String cantPupitres;
    @Column(name = "tipo_pizarron")
    @Enumerated(EnumType.STRING)
    private Pizarron pizarron;
    @Column(name = "fecha_alta")
    private LocalDate fechaALta;
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;
    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )@JoinColumn(name = "pabellon_id",foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
    private Pabellon pabellon;

    @PrePersist
    public void antesDePersistir(){
        this.fechaALta=LocalDate.now();
    }
    @PreUpdate
    public void antesDeUpdate(){
        this.fechaModificacion=LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(id, aula.id) && Objects.equals(nroAula, aula.nroAula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nroAula);
    }
}
