//Contém os endpoints HTTP da aplicação (REST ou MVC)
//São anotadas com @RestController ou @Controller
//Recebem requisições, chamam o service correspondente e retornam respostas
//Nunca devem conter lógica complexa — apenas chamar o service

package com.example.demo.controller;

import com.example.demo.model.Pessoa;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public List<Pessoa> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Pessoa buscar(@PathVariable Long id) {
        return service.buscar(id).orElse(null);
    }

    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return service.criar(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return service.atualizar(id, pessoa);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/search")
    public List<Pessoa> searchByNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/search/containing")
    public List<Pessoa> searchByNomeContaining(@RequestParam String parteNome) {
        return service.buscarPorNomeContendo(parteNome);
    }
}