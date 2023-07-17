package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.will1184.springproyectouniversidad.model.entity.Carrera;
import org.will1184.springproyectouniversidad.repository.CarreraRepository;
import org.will1184.springproyectouniversidad.service.contratos.CarreraDao;

import java.util.Optional;
@Service
public class CarreraDaoImpl implements CarreraDao {
    @Autowired
    private CarreraRepository repository;

    @Override
    @Transactional
    public Optional<Carrera> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Carrera save(Carrera carrera) {
        return repository.save(carrera);
    }

    @Override
    @Transactional
    public Iterable<Carrera> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
