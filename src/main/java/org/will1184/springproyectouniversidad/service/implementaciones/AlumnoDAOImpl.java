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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO {

    @Autowired
    public AlumnoDAOImpl(@Qualifier("alumnoRepository") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findAll() {
        Iterable<Persona> personas = super.findAll();
        List<Persona> alumnos = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona instanceof Alumno) {
                alumnos.add(persona);
            }
        }
        return alumnos;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findById(Integer id) {
        Optional<Persona> optionalPersona=super.findById(id);
        Persona persona = optionalPersona.get();
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
