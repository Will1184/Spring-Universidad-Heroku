package org.will1184.springproyectouniversidad.service.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.will1184.springproyectouniversidad.model.entity.Empleado;
import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.enums.TipoEmpleado;
import org.will1184.springproyectouniversidad.repository.PersonaRepository;
import org.will1184.springproyectouniversidad.service.contratos.EmpleadoDAO;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {
    @Autowired
    public EmpleadoDAOImpl(@Qualifier("empleadoRepository") PersonaRepository repository) {
        super(repository);
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarEmpleadosPorTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return repository.buscarEmpleadosPorTipoEmpleado(tipoEmpleado);
    }
}
