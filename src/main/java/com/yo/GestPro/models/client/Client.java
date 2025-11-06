package com.yo.GestPro.models.client;

import com.yo.GestPro.models.product.Product;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Entity(name = "client")
public class Client {
    @Column(nullable = false, name = "id_client")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    @Column(nullable = false, unique = true, name = "login_client")
    private String loginClient;
    @Column(nullable = false, name = "password_client")
    private String passwordClient;
    @Column(nullable = false)
    private ClientAccount clientAccount;

    @OneToMany(mappedBy = "client")
    private List<Product> products;

    public Client(String loginClient, String passwordClient, ClientAccount clientAccount) {
        this.loginClient = loginClient;
        this.passwordClient = passwordClient;
        this.clientAccount = clientAccount;
    }

    public Client(){

    }
}
