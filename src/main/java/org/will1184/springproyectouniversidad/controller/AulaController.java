package org.will1184.springproyectouniversidad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.will1184.springproyectouniversidad.exception.BadRequestException;
import org.will1184.springproyectouniversidad.model.entity.*;
import org.will1184.springproyectouniversidad.model.enums.Pizarron;
import org.will1184.springproyectouniversidad.service.contratos.AulaDAO;
import org.will1184.springproyectouniversidad.service.contratos.PabellonDAO;

import java.util.Optional;

public class AulaController extends GenericController<Aula, AulaDAO>{
    private final PabellonDAO pabellonDAO;
      public AulaController(AulaDAO service, PabellonDAO pabellonDAO) {
        super(service);
          this.pabellonDAO = pabellonDAO;
      }

    @GetMapping("/aulas-pizarras")
    public  Iterable<Aula>findAulasByPizarron(Pizarron pizarron){
          return service.findAulasByPizarron(pizarron);
    }
    @GetMapping("/aulas-pabellon")
    public Iterable<Aula>findAulasByPabellonNombre(@RequestBody String nombre){
          return service.findAulasByPabellonNombre(nombre);
    }
    @GetMapping("/nroaulas")
    public Optional<Aula> findAulaByNroAula(Integer nroAula){
          return service.findAulaByNroAula(nroAula);
    }

    @PutMapping("/{idAula}/pabellon/{idPabellon}")
    public Aula asignarPabellonAula(@PathVariable Integer idAula, @PathVariable Integer idPabellon){
        Optional<Aula> oAula = service.findById(idAula);
        if(!oAula.isPresent()) {
            throw new BadRequestException(String.format("Aula con id %d no existe", idAula));
        }
        Optional<Pabellon> oPabellon = pabellonDAO.findById(idPabellon);
        if(!oPabellon.isPresent()){
            throw new BadRequestException(String.format("Pabellon con id %d no existe", idPabellon));
        }

        Pabellon pabellon = oPabellon.get();
        Aula aula = oAula.get();

        aula.setPabellon(pabellon);
        return service.save(aula);
    }
}
