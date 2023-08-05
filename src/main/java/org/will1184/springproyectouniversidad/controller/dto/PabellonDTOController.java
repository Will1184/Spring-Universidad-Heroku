package org.will1184.springproyectouniversidad.controller.dto;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.will1184.springproyectouniversidad.model.entity.Aula;
import org.will1184.springproyectouniversidad.model.entity.Pabellon;
import org.will1184.springproyectouniversidad.service.contratos.AulaDAO;
import org.will1184.springproyectouniversidad.service.contratos.PabellonDAO;

@RestController
@RequestMapping("/pabellones")
@Tag(name = "aulas", description = "Catálogo de aulas")
@ConditionalOnProperty(prefix = "app",name = "controller.enable-dto",havingValue = "true")

public class PabellonDTOController extends GenericDTOController<Pabellon, PabellonDAO> {


    public PabellonDTOController(PabellonDAO service) {
        super(service, "pabellon");
    }
}
