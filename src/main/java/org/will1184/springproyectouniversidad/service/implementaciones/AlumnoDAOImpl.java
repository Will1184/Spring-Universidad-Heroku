package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Carrera;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.entity.Profesor;
import org.will1184.springproyectouniversidad.repository.AlumnoRepository;
import org.will1184.springproyectouniversidad.repository.PersonaRepository;
import org.will1184.springproyectouniversidad.service.contratos.AlumnoDAO;
import org.will1184.springproyectouniversidad.service.contratos.CarreraDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO {

    @Autowired
    public AlumnoDAOImpl(@Qualifier("alumnoRepository") PersonaRepository repository) {
        super(repository);
    }

//    @Override
//    public Iterable<Aula> findAulasByPizarron(Pizarron pizarron) {
//        return null;
//    }
//
//    @Override
//    public Iterable<Aula> findAulasByPabellonNombre(String nombre) {
//        return null;
//    }
//
//    @Override
//    public Optional<Aula> findAulaByNroAula(Integer nroAula) {
//        return Optional.empty();
//    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findAll() {
        Iterable<Persona> personas = super.findAll();
        List<Persona> alumnos = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona instanceof Alumno) {
                alumnos.add((Alumno) persona);
            }
        }
        return alumnos;
    }

//    @Override
//    @Transactional
//    public Persona save(Persona entidad) {
//        if (entidad instanceof Alumno) {
//            Alumno alumno = (Alumno) entidad;
//            if (alumno.getCarrera() != null && alumno.getCarrera().getId() != null) {
//                Carrera carreraExistente = carreraDAO.findById(alumno.getCarrera().getId())
//                        .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada"));
//                alumno.setCarrera(carreraExistente);
//            }
//        }
//        return super.save(entidad);
//    }
//
    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarAlumnosPorCarrera(String carrera) {
        return ((AlumnoRepository)repository).buscarAlumnosPorCarrera(carrera);
    }
}
