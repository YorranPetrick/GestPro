# 🛒 Sistema de Gerenciamento de Produtos
Este projeto é um sistema para gerenciar usuários e produtos, utilizando o padrão CRUD (Criar, Ler, Atualizar, Deletar) e implementando funcionalidades avançadas como notificações com RabbitMQ e versionamento do banco de dados com Flyway.

## Funcionalidades

1 . CRUD de Produtos e Usuários

* Produtos: Cadastro, atualização, visualização e remoção de produtos.

* Usuários: Cadastro, autenticação e gerenciamento de dados dos usuários
  
  
2 . Notificação via RabbitMQ

* Utilização do RabbitMQ para enviar notificações quando a quantidade de um produto ultrapassar os limites mínimo ou máximo configurados.

3 . Versionamento de Banco de Dados com Flyway

* Migrations para gerenciar as versões do banco de dados, garantindo a consistência e controle sobre as alterações do esquema.

4 . Banco de Dados PostgreSQL

* O banco de dados utilizado é o PostgreSQL, garantindo escalabilidade e consistência para armazenar dados de clientes e produtos.

## Tabelas SQL

Tabela client
```sql

CREATE TABLE client (
    id_client UUID PRIMARY KEY NOT NULL,
    login_client VARCHAR(255) UNIQUE NOT NULL,
    password_client VARCHAR(255) NOT NULL,
    client_account VARCHAR(10) NOT NULL
);
```
Tabela product

```sql
CREATE TABLE product (
    id_product UUID PRIMARY KEY NOT NULL,
    name_product VARCHAR(255) NOT NULL,
    description_product TEXT,
    price_product DOUBLE PRECISION NOT NULL,
    client_id UUID,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id_client)
);
```
Alterações na Tabela product

```sql
ALTER TABLE product ADD COLUMN minimum_quantity INTEGER;
ALTER TABLE product ADD COLUMN maximum_quantity INTEGER;
ALTER TABLE product ADD COLUMN actual_quantity INTEGER;
```

## Segurança

1 . Pacote de Validação

* Utilização do pacote Validation para garantir que as entradas de dados sejam válidas e consistentes.

2 . Spring Security e JWT

* Implementação de autenticação e autorização utilizando o Spring Security.

* JWT (JSON Web Tokens) para gerenciamento de sessões e autenticação dos usuários.

3 . Criptografia

* Criptografia de senhas e dados sensíveis utilizando algoritmos robustos.

4 . Classe AccessDeniedHandler

* Implementação da classe AccessDeniedHandler para lidar com acessos não autorizados, personalizando a resposta da aplicação.

5 . Classe GlobalExceptionHandler

* Gerenciamento global de exceções, para garantir que erros sejam tratados de forma adequada e mensagens claras sejam retornadas.

## Arquitetura

```Java
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── yo/
│   │           └── GestPro/
│   │               ├── controller/
│   │               │   └── ClientController.java
│   │               │   └── ProductController.java
│   │               │
│   │               ├── exception/
│   │               │   └── GlobalExceptionHendler.java
│   │               │   └── CustomAccessDeniedHandler.java
│   │               │
│   │               ├── infra/
│   │               │   ├── filter/
│   │               │   │   └── RequestFilter.java
│   │               │   └── security/
│   │               │       ├── SecurityConfiguration.java
│   │               │       └── TokenJwt.java
│   │               │
│   │               ├── models/
│   │               │   ├── client/
│   │               │   │   ├── Client.java
│   │               │   │   ├── ClientAccount.java
│   │               │   │   ├── ClientCreateDto.java
│   │               │   │   └── ClientLoginDto.java
│   │               │   │
│   │               │   ├── error/
│   │               │   │   ├── ErrorField.java
│   │               │   │   └── ErrorResponse.java
│   │               │   │
│   │               │   ├── product/
│   │               │   │   └── CreateProductDto.java
│   │               │   │   └── Product.java
│   │               │   │
│   │               │   └── token/
│   │               │       └── TokenResponse.java
│   │               │
│   │               ├── repository/
│   │               │   ├── ClientRepository.java
│   │               │   └── ProductRepository.java
│   │               │
│   │               └── service/
│   │                   ├── client/
│   │                   │   ├── ClientAuthenticationService.java
│   │                   │   ├── ClientService.java
│   │                   │   └── GestProApplication.java
│   │                   │
│   │                   └── product/
│   │                       ├── ProductService.java
│   │
│   └── resources/
│       ├── application.properties
│       └── db.migration/
│           ├── V1__create_table_client.sql
│           └── V2__Create_table_product.sql
│
└── test/
└── ...
```
