//Contém as classes que representam tabelas do banco de dados
//São anotadas com @Entity
//Cada classe corresponde a uma tabela, com atributos correspondendo a colunas

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private LocalDate data;

    // Participantes
    @ManyToMany
    @JoinTable(
            name = "pessoa_atividade",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "pessoa_id")
    )
    @JsonManagedReference // indica para o Jackson onde parar a serialização
    private List<Pessoa> participantes;

    public Atividade() {}

    public Atividade(String nome, String descricao, LocalDate data) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public List<Pessoa> getParticipantes() { return participantes; }
    public void setParticipantes(List<Pessoa> participantes) { this.participantes = participantes; }
}
