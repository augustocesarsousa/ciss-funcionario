# Teste desenvolvedor CISS

## Descrição do desafio

Linguagem back-end: Java.
Linguagem front-end: Angular ou Flutter.

Do projeto:
Projeto tem o objetivo de efetuar um CRUD de funcionário.

Dos atributos do Funcionário:

- Nome (Entre 2 e 30 caracteres);
- Sobrenome (Entre 2 e 50 caracteres);
- e-mail (Validar e-mail);
- Número do NIS (PIS) (Somente números).

Das funcionalidades (Utilizando APIs):

- Inserção de um Funcionário;
- Exclusão de um Funcionário;
- Atualização de um Funcionário;
- Listagem de um Funcionário.

Do desenvolvimento:

- Utilize o padrão de desenvolvimento que acredita ser o mais correto.

Da entrega dos fontes:

- Faça a entrega dos fontes da forma que acredita ser a mais correta.

Obs.:
Pode utilizar banco de dados ou em memória.

---

## Desafio

### Backend

- Linguagem: Java
- Framework: Spring Boot
- Banco de dados: H2 (memória)

### CRUD

- Padrão DTO

- Projeto em camadas

  - Entity
  - Repository
  - Custom Respository
  - Service
  - Controller

- Consulta ao banco

  - JPQL
  - Query Methods

- Tratamento de exceções

  - Exceções customizadas
  - Bean Validation
  - Constraint Validator

- Testes

  - Testes unitários
  - Testes de integração
  - JUnit
  - Mockito & MockBean

- Documentação
  - Swagger

## Frontend

- Linguagem: TypeScript
- Framework: Angular

### Layout e navegação

- HTML
- CSS
  - Bootstrap
  - Responsividade
- Rotas
  - RouterModule
- Comunicação com API
  - HttpClient
    - GET
    - POST
    - PUT
    - DELETE
- Formulários
  - ReactiveForms
- Paginação e filtros
- Notificações
  - ToastrModule

## Deploy

- Docker
- DockerCompose

## Imagens do projeto

**Backend**

1. Swagger

![Backend Swagger](/images/01-swagger.jpg)

**Frontend**

1. Tela inicial

![Frontend tela inicial](/images/02-tela-inicial.jpg)

2. Tela consulta

![Frontend tela consulta](/images/03-tela-consulta.jpg)

3. Tela cadastro

![Frontend tela cadastro](/images/04-tela-cadastro.jpg)

3. Tela edição

![Frontend tela edição](/images/05-tela-edicao.jpg)

## Executando o projeto

### Docker

- Requisitos:

  - Docker;
  - Docker-compose.

Executar o comando abaixo via terminal na rais do projeto:

```
docker-compose up
```

### Manual

**Backend**

- Requisitos

  - Java 17
  - Maven 3.8.x

Executar os comandos abaixo via terminal na pasta backend:

```
mvn install
mvn spring-boot:run
```

**Frontend**

- Requisitos

  - Node 14
  - Npm (compatíval com a versão do Node)
  - Angular 15

Executar os comandos abaixo via terminal na pasta frontend:

```
npm i
ng s -o
```

### Links

- Backend Swagger: <http://localhost:8081/swagger-ui.html#/>
- Frontend: <http://localhost:4201/>
