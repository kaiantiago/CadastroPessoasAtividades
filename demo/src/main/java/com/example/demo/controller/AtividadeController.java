//Contém os endpoints HTTP da aplicação (REST ou MVC)
//São anotadas com @RestController ou @Controller
//Recebem requisições, chamam o service correspondente e retornam respostas
//Nunca devem conter lógica complexa — apenas chamar o service

package com.example.demo.controller;

import com.example.demo.model.Atividade;
import com.example.demo.model.Pessoa;
import com.example.demo.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService service;

    // Listar todas as atividades
    @GetMapping
    public List<Atividade> listar() {
        return service.listar();
    }

    // Buscar atividade por ID
    @GetMapping("/{id}")
    public Optional<Atividade> buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Criar nova atividade
    @PostMapping
    public Atividade criar(@RequestBody Atividade atividade, @RequestParam Long pessoaId) {
        try {
            return service.criar(atividade, pessoaId);
        } catch (Exception e) { //Se o usuário não for staff, retorna HTTP 403 Forbidden com a mensagem da exceção
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    // Atualizar atividade
    @PutMapping("/{id}")
    public Atividade atualizar(@PathVariable Long id, @RequestBody Atividade atividade) throws Exception {
        return service.atualizar(id, atividade);
    }

    // Deletar atividade
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    // Inscrever pessoa em atividade
    @PostMapping("/{atividadeId}/inscrever/{pessoaId}")
    public Atividade inscreverPessoa(
            @PathVariable Long atividadeId,
            @PathVariable Long pessoaId
    ) throws Exception {
        return service.inscreverPessoa(atividadeId, pessoaId);
    }

    // Listar participantes de uma atividade
    @GetMapping("/{atividadeId}/participantes")
    public List<Pessoa> listarParticipantes(@PathVariable Long atividadeId) {
        return service.listarParticipantes(atividadeId);
    }
}