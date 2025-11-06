package com.yo.GestPro.models.product;

import com.yo.GestPro.models.client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Product {
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    @Column(nullable = false)
    private String nameProduct;
    private String descriptionProduct;
    @Column(nullable = false)
    private double priceProduct;

    @ManyToOne
    private Client client;

    public Product(String nameProduct, String descriptionProduct, double priceProduct) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
    }

    Product(){

    }
}
