//Contém os endpoints HTTP da aplicação (REST ou MVC)
//São anotadas com @RestController ou @Controller
//Recebem requisições, chamam o service correspondente e retornam respostas
//Nunca devem conter lógica complexa — apenas chamar o service

package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class OlaController {

    @GetMapping("/ola")
    public String ola() {
        return "Olá, mundo!";
    }

    @GetMapping("/ola/{nome}")
    public String olaNome(@PathVariable String nome) {
        return "Olá, " + nome + "!";
    }
}