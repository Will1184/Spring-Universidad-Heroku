package org.will1184.springproyectouniversidad.comandos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.will1184.springproyectouniversidad.service.contratos.AulaDAO;
import org.will1184.springproyectouniversidad.service.contratos.CarreraDAO;
import org.will1184.springproyectouniversidad.service.contratos.PabellonDAO;
import org.will1184.springproyectouniversidad.service.contratos.PersonaDAO;

@Component
@Order(1)
public class InitCommand {
    @Autowired
    @Qualifier(value = "alumnoDAOImpl")
    private PersonaDAO servicioAlumno;

//    @Autowired
//    @Qualifier(value = "empleadoDAOImpl")
//    private PersonaDAO servicioEmpleado;
//    @Autowired
//    @Qualifier(value = "profesorDAOImpl")
//    private PersonaDAO servicioProfesor;
//    @Autowired
//    private AulaDAO servicioAula;
//    @Autowired
//    private PabellonDAO servicioPabellon;
//    @Autowired
//    private CarreraDAO servicioCarrera;
}
