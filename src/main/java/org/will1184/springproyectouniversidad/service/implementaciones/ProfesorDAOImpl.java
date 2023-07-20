package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.entity.Profesor;
import org.will1184.springproyectouniversidad.repository.PersonaRepository;
import org.will1184.springproyectouniversidad.service.contratos.ProfesorDAO;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO {
    @Autowired
    public ProfesorDAOImpl(@Qualifier("profesorRepository") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarProfesoresPorCarrera(String carrera) {
        return repository.buscarProfesoresPorCarrera(carrera);
    }
}
