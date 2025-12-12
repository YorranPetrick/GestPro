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