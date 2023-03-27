package io.spring.start.projetoweb.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teste")
public class TesteController {
    // GET - Listagem/item

//    @GetMapping
//    public String testeGet() {
//        return "Olá mundo";
//    }

    @GetMapping("/{id}")
    public String testeGet(@RequestParam(name="nome", defaultValue = "") String nome,
                           @RequestParam(name="idade", defaultValue = "0") String idade,
                           @PathVariable("id") String id) {
        try{
            return "ID: " + id + "- " + "Eu sou " + nome+" e tenho " + Integer.parseInt(idade) + " anos";
        } catch (Exception e) {
            return "Dados inválidos";
        }

    }

    //url ?nome=pedro&idade=26
}
