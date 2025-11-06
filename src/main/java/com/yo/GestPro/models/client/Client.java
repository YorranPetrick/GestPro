package com.yo.GestPro.models.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class Client {
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    @Column(nullable = false, unique = true)
    private String loginClient;
    @Column(nullable = false)
    private String passwordClient;
    @Column(nullable = false)
    private ClientAccount clientAccount;

    public Client(String loginClient, String passwordClient, ClientAccount clientAccount) {
        this.loginClient = loginClient;
        this.passwordClient = passwordClient;
        this.clientAccount = clientAccount;
    }

    Client(){

    }
}
