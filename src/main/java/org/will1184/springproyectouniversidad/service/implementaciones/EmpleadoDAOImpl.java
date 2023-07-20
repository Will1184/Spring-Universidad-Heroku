package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.will1184.springproyectouniversidad.model.entity.Empleado;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.entity.Profesor;
import org.will1184.springproyectouniversidad.model.enums.TipoEmpleado;
import org.will1184.springproyectouniversidad.repository.AlumnoRepository;
import org.will1184.springproyectouniversidad.repository.EmpleadoRepository;
import org.will1184.springproyectouniversidad.repository.PersonaRepository;
import org.will1184.springproyectouniversidad.service.contratos.EmpleadoDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {
    private EmpleadoRepository repository;

    @Autowired
    public EmpleadoDAOImpl(@Qualifier("empleadoRepository") EmpleadoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findAll() {
        Iterable<Persona> personas = super.findAll();
        List<Persona> empleados = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona instanceof Empleado) {
                empleados.add((Empleado) persona);
            }
        }
        return empleados;
    }
    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarEmpleadosPorTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return repository.buscarEmpleadosPorTipoEmpleado(tipoEmpleado);
    }
}

