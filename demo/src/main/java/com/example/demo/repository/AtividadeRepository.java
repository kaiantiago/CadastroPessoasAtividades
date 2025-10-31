//Contém interfaces que fazem a comunicação com o banco
//Não implementam lógica complexa, apenas operações CRUD básicas e consultas customizadas

package com.example.demo.repository;

import com.example.demo.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    // Métodos customizados também podem ser adicionados aqui
    // List<Atividade> findByNomeContaining(String nome);

    // Buscar atividades por nome exato
    List<Atividade> findByNome(String nome);

    // Buscar atividades que contenham parte do nome
    List<Atividade> findByNomeContaining(String parteNome);

    // Buscar atividades que comecem com o nome
    List<Atividade> findByNomeStartingWith(String prefixo);

    // Buscar atividades que terminem com o nome
    List<Atividade> findByNomeEndingWith(String sufixo);

    // Buscar atividades por data exata
    List<Atividade> findByData(LocalDate data);

    // Buscar atividades entre datas
    List<Atividade> findByDataBetween(LocalDate inicio, LocalDate fim);
}
