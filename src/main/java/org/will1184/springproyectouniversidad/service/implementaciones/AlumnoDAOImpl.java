package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.will1184.springproyectouniversidad.model.entity.Alumno;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.repository.AlumnoRepository;
import org.will1184.springproyectouniversidad.repository.PersonaRepository;
import org.will1184.springproyectouniversidad.service.contratos.AlumnoDAO;
import java.util.Optional;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO {
    @Autowired
    public AlumnoDAOImpl(@Qualifier("alumnoRepository") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findById(Integer id) {
        Optional<Persona> optionalPersona=super.findById(id);
        Persona persona = optionalPersona.orElse(null);
        if (persona instanceof Alumno){
            return optionalPersona;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarAlumnosPorCarrera(String carrera) {
        return ((AlumnoRepository)repository).buscarAlumnosPorCarrera(carrera);
    }
}
