package org.will1184.springproyectouniversidad.comandos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.will1184.springproyectouniversidad.service.contratos.AulaDAO;

@Component
@Order(3)
public class AulaCommand implements CommandLineRunner {
    @Autowired
    private AulaDAO aulaDAO;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------- ************* Aula Command ********** --------");
        aulaDAO.save(ObjetosDummy.getAula123());
        System.out.println(aulaDAO.findAulaByNroAula(ObjetosDummy.getAula123().getNroAula()));
    }
}
