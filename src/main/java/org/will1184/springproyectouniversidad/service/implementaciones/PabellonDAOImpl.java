package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.will1184.springproyectouniversidad.model.entity.Pabellon;
import org.will1184.springproyectouniversidad.repository.PabellonRepository;
import org.will1184.springproyectouniversidad.service.contratos.PabellonDAO;

@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO {
    @Autowired
    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Pabellon> findAllPabellonByLocalidad(String localidad) {
        return repository.findAllPabellonByLocalidad(localidad);
    }

    @Override
    public Iterable<Pabellon> findAllPabellonByNombre(String nombre) {
        return repository.findAllPabellonByNombre(nombre);
    }
}
