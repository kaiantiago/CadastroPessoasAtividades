//Contém a lógica de negócio da aplicação
//Chamam os métodos do repository e aplicam regras antes de salvar ou buscar dados
//São anotadas com @Service
//Mantêm o controller limpo, separando lógica de HTTP e lógica do sistema

package com.example.demo.service;

import com.example.demo.model.Atividade;
import com.example.demo.model.Pessoa;
import com.example.demo.repository.AtividadeRepository;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    // Listar todas as atividades
    public List<Atividade> listar() {
        return atividadeRepository.findAll();
    }

    // Buscar atividade por ID
    public Optional<Atividade> buscarPorId(Long id) {
        return atividadeRepository.findById(id);
    }

    // Criar nova atividade
    public Atividade criar(Atividade atividade, Long pessoaId) throws Exception {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new Exception("Pessoa não encontrada"));

        if (!pessoa.isStaff()) {
            throw new Exception("Apenas pessoas staff podem criar atividades");
        }

        return atividadeRepository.save(atividade);
    }

    // Atualizar atividade
    public Atividade atualizar(Long id, Atividade atividadeAtualizada) throws Exception {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new Exception("Atividade não encontrada"));

        atividade.setNome(atividadeAtualizada.getNome());
        atividade.setDescricao(atividadeAtualizada.getDescricao());
        atividade.setData(atividadeAtualizada.getData());

        return atividadeRepository.save(atividade);
    }

    // Deletar atividade
    public void deletar(Long id) {
        atividadeRepository.deleteById(id);
    }

    // Inscrever pessoa em atividade
    public Atividade inscreverPessoa(Long atividadeId, Long pessoaId) throws Exception {
        Atividade atividade = atividadeRepository.findById(atividadeId)
                .orElseThrow(() -> new Exception("Atividade não encontrada"));
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new Exception("Pessoa não encontrada"));

        // Evita duplicidade
        if (!atividade.getParticipantes().contains(pessoa)) {
            atividade.getParticipantes().add(pessoa);
        }

        return atividadeRepository.save(atividade);
    }

    // Lista os participantes da atividade
    public List<Pessoa> listarParticipantes(Long atividadeId) {
        Atividade atividade = atividadeRepository.findById(atividadeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atividade não encontrada"));

        return atividade.getParticipantes();
    }

}