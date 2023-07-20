package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.will1184.springproyectouniversidad.model.entity.Aula;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.enums.Pizarron;
import org.will1184.springproyectouniversidad.repository.AulaRepository;
import org.will1184.springproyectouniversidad.repository.PersonaRepository;
import org.will1184.springproyectouniversidad.service.contratos.AulaDAO;

import java.util.Optional;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO {


    @Autowired
    public AulaDAOImpl( AulaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Aula> findAulasByPizarron(Pizarron pizarron) {
        return repository.findAulasByPizarron(pizarron);
    }

    @Override
    public Iterable<Aula> findAulasByPabellonNombre(String nombre) {
        return repository.findAulasByPabellonNombre(nombre);
    }

    @Override
    public Optional<Aula> findAulaByNroAula(Integer nroAula) {
        return repository.findAulaByNroAula(nroAula);
    }

}
