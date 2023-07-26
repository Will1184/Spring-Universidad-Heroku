package org.will1184.springproyectouniversidad.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restapi")
public class PrimerRestController {
    @GetMapping("/hola-mundo")
    public ResponseEntity<String> holaMundo(){
        return new ResponseEntity<>("Hola mundo! =D", HttpStatus.ACCEPTED);
    }
}
