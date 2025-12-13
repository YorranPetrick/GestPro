package com.yo.GestPro.models.product;

import com.yo.GestPro.models.client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "product")
public class Product {
    @Id
    @Column(nullable = false, name = "id_product")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    @Column(nullable = false, name = "name_product")
    private String nameProduct;
    @Column(name = "description_product")
    private String descriptionProduct;
    @Column(nullable = false, name = "price_product")
    private double priceProduct;
    @Column(nullable = false, name = "minimum_qantity")
    private Integer minimumQuantity;
    @Column(nullable = false, name = "maximum_quantity")
    private Integer maximumQuantity;

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
