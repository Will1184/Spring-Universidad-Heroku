package org.will1184.springproyectouniversidad.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Empleado;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.entity.Profesor;
import org.will1184.springproyectouniversidad.model.enums.TipoEmpleado;

import java.util.Optional;

@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona,Integer> {
    @Query("select p from Persona p where p.nombre = ?1 and p.apellido = ?2")
    Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);
    @Query("select p from Persona p where p.dni = ?1")
    Optional<Persona> buscarPorDni(String dni);
    @Query("select p from Persona p where p.apellido like %?1%")
    Iterable<Persona> buscarPersonaPorApellido(String apellido);

    @Query("SELECT e FROM Empleado e  WHERE e.tipoEmpleado=?1")
    Iterable<Persona> buscarEmpleadosPorTipoEmpleado(TipoEmpleado tipoEmpleado);

    @Query("SELECT a FROM Alumno a JOIN a.carrera c WHERE c.nombre=?1")
    Iterable<Persona> buscarAlumnosPorCarrera(String carrera);
    @Query("SELECT p FROM Profesor p JOIN p.carreras c WHERE c.nombre =?1")
    Iterable<Persona> buscarProfesoresPorCarrera(String carrera);
}
