//Contém a lógica de negócio da aplicação
//Chamam os métodos do repository e aplicam regras antes de salvar ou buscar dados
//São anotadas com @Service
//Mantêm o controller limpo, separando lógica de HTTP e lógica do sistema

package com.example.demo.service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> listar() {
        return repository.findAll();
    }

    public Optional<Pessoa> buscar(Long id) {
        return repository.findById(id);
    }

    public Pessoa criar(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        pessoa.setId(id);
        return repository.save(pessoa);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Pessoa> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Pessoa> buscarPorNomeContendo(String parteNome) {
        return repository.findByNomeContaining(parteNome);
    }
}
