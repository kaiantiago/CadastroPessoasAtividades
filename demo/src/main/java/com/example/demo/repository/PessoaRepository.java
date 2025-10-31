//Contém interfaces que fazem a comunicação com o banco
//Não implementam lógica complexa, apenas operações CRUD básicas e consultas customizadas

package com.example.demo.repository;

import com.example.demo.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Você poderá criar consultas customizadas aqui depois

    // Buscar pessoas por nome exato
    List<Pessoa> findByNome(String nome);

    // Buscar pessoas que contenham parte do nome
    List<Pessoa> findByNomeContaining(String parteNome);

    // Buscar pessoas que comecem com o nome
    List<Pessoa> findByNomeStartingWith(String prefixo);

    // Buscar pessoas que terminem com o nome
    List<Pessoa> findByNomeEndingWith(String sufixo);
}
