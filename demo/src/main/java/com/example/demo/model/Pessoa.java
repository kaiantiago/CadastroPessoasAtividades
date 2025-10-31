//Contém as classes que representam tabelas do banco de dados
//São anotadas com @Entity
//Cada classe corresponde a uma tabela, com atributos correspondendo a colunas

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private boolean staff;

    @ManyToMany(mappedBy = "participantes")
    @JsonBackReference // indica para o Jackson onde parar a serialização
    private List<Atividade> atividades;

    public Pessoa() {}

    public Pessoa(String nome, String email, boolean staff) {
        this.nome = nome;
        this.email = email;
        this.staff = staff;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isStaff() { return staff; }
    public void setStaff(boolean staff) { this.staff = staff; }

    public List<Atividade> getAtividades() { return atividades; }
    public void setAtividades(List<Atividade> atividades) { this.atividades = atividades; }
}