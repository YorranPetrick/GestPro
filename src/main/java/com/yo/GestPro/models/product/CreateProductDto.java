package com.yo.GestPro.models.product;

import com.yo.GestPro.models.client.Client;

public record CreateProductDto(
        String nameProduct,
        String descriptionProduct,
        double priceProduct,
        Integer minimumQuantity,
        Integer maximumQuantity,
        Client client
) {

    public Product toEntity(){
        return new Product(
                this.nameProduct,
                this.descriptionProduct,
                this.priceProduct,
                this.minimumQuantity,
                this.maximumQuantity,
                this.client
        );
    }
}
