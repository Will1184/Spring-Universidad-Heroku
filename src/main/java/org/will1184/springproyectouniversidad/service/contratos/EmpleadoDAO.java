package org.will1184.springproyectouniversidad.service.contratos;

import org.will1184.springproyectouniversidad.model.entity.Persona;
import org.will1184.springproyectouniversidad.model.enums.TipoEmpleado;

public interface EmpleadoDAO extends PersonaDAO {
    Iterable<Persona> buscarEmpleadosPorTipoEmpleado(TipoEmpleado tipoEmpleado);
}
