# 🛒 Sistema de Gerenciamento de Produtos
API REST desenvolvida em Spring Boot que facilita o controle de estoque e produtos de forma simples e eficiente. Com ela, é possível cadastrar, atualizar e consultar produtos em tempo real, garantindo maior organização e agilidade no gerenciamento.

Além disso, o sistema conta com recursos inteligentes, como alertas automáticos de notificação quando a quantidade de um produto atinge o limite mínimo definido. Isso ajuda empresas a manterem o estoque sempre sob controle e evita perdas por falta de reposição.

Uma solução prática, escalável e moderna para quem busca otimizar processos e ter mais segurança na gestão de produtos. 🚀


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
│   │               │
│   │               ├── exception/
│   │               │   └── GlobalExceptionHendler.java
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
│   │                   └── client/
│   │                       ├── ClientAuthenticationService.java
│   │                       ├── ClientService.java
│   │                       └── GestProApplication.java
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