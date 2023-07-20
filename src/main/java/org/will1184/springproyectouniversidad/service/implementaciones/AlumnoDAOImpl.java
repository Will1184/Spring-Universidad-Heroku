package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.repository.PersonaRepository;
import org.will1184.springproyectouniversidad.service.contratos.AlumnoDAO;

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
    public Iterable<Persona> buscarAlumnosPorCarrera(String carrera) {
        return repository.buscarAlumnosPorCarrera(carrera);
    }
}
