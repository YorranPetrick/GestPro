package com.yo.GestPro.models.product;

import com.yo.GestPro.models.client.Client;

public record CreateProductDto(
        String nameProduct,
        String descriptionProduct,
        double priceProduct,
        Integer atualQuantity,
        Integer minimumQuantity,
        Integer maximumQuantity
) {

    public Product toEntity(Client client){
        return new Product(
                this.nameProduct,
                this.descriptionProduct,
                this.priceProduct,
                this.atualQuantity,
                this.minimumQuantity,
                this.maximumQuantity,
                client
        );
    }
}
