<h1> Crud via API Rest - Java, Spring Boot, Flyway, MySQL </h1>
<h3> Descrição : </h3>
API REST que faz operações CRUD (Create, Read, Update, Delete) sobre usuários armazenados em um banco de dados PostgreSQL.
<h3> Demonstração em video : </h3>

https://github.com/user-attachments/assets/98e8417e-b5ed-432e-8917-8ff469948cbe

**# User Management API**

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen) ![Java](https://img.shields.io/badge/Java-17-blue) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14.0-blue)

Uma API RESTful simples para criar, ler, atualizar e deletar usuários, construída com Java e Spring Boot.

---

## 🚀 Visão Geral

Este projeto implementa uma arquitetura em camadas, utilizando os conceitos de **Controller**, **Service** e **Repository**. A comunicação é feita através de requisições HTTP, seguindo as convenções REST.

---

## ✨ Principais Funcionalidades

* **Arquitetura em camadas bem definida**

  * Separação clara entre **Controller**, **Service** e **Repository**, promovendo alta coesão e baixo acoplamento.

* **Uso de DTOs como registros (Java `record`)**

  * `CreateUserDTO` e `UpdateUserDTO` deixam explícita a estrutura de entrada, facilitando manutenção, validação e documentação automática.

* **Injeção de dependências via construtor**

  * Tornando o código mais desacoplado e facilitando a **testabilidade** (sem uso de `@Autowired` diretamente nos campos).

* **API RESTful seguindo convenções HTTP**

  * **POST** `/v1/users` → cria novo usuário (**201 Created** + cabeçalho `Location`).
  * **GET** `/v1/users/{id}` → pesquisa por ID (**200 OK** ou **404 Not Found**).
  * **GET** `/v1/users` → lista todos os usuários (**200 OK**).
  * **PUT** `/v1/users/{id}` → atualiza parcialmente um usuário (**204 No Content**).
  * **DELETE** `/v1/users/{id}` → remove usuário (**204 No Content**).

* **Tratamento de entradas e conversão de tipos**

  * Converte `String` → `Integer` de forma segura e usa `Optional` para evitar `NullPointerException`.

* **Uso de Spring Data JPA**

  * `UserRepository` estende `JpaRepository`, aproveitando métodos prontos como `findById`, `findAll`, `save`, `existsById` e `deleteById`.

* **Atualização condicional de campos**

  * No método de **update**, só altera um campo se o valor no DTO for não-nulo, permitindo comportamento de *patch*.

* **Resposta padronizada e amigável**

  * Uso de `ResponseEntity` para controlar status HTTP e corpo de resposta de forma explícita.

* **Script de criação de tabela SQL**

  ```sql
  CREATE TABLE user_tb (
     id SERIAL PRIMARY KEY,
     first_name VARCHAR(255),
     last_name  VARCHAR(255),
     email      VARCHAR(255),
     password   VARCHAR(255)
  );
  ```

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 2.7.0**
* **Spring Web (Spring MVC)**
* **Spring Data JPA**
* **PostgreSQL 14**
* **Maven**

---

## 📦 Como Executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/seu-projeto.git
   ```
2. Configure o banco de dados em `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/seu_db
   spring.datasource.username=usuario
   spring.datasource.password=senha
   ```
3. Execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

Pronto! A API estará disponível em `http://localhost:8080/v1/users`.
