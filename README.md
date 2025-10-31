# CadastroPessoasAtividades
Uma aplicação simples em Java utilizando SpringBoot em que é possível realizar o cadastro de pessoas, cadastro de atividades e o cadastro de pessoas em atividades por meio de instruções em JSON. Os dados ficam salvos em um banco de dados SQL.

Instruções exemplo:

Criar pessoa:
POST http://localhost:8080/api/pessoas
{
  "nome": "João Aluno",
  "email": "joao@gmail.com",
  "staff": false
}

Criar atividade:
POST http://localhost:8080/atividades?pessoaId=1
{
  "nome": "Treinamento de Spring Boot",
  "descricao": "Introdução ao framework",
  "data": "2025-11-10"
}

Inscrever pessoa em atividade: (apenas Staff == true)
POST http://localhost:8080/atividades/1/inscrever/2
