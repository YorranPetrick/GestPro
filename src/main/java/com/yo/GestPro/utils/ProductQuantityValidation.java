package com.yo.GestPro.utils;

import com.yo.GestPro.infra.messaging.RabbitProducer;
import com.yo.GestPro.models.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductQuantityValidation {

    private final RabbitProducer rabbitProducer;

    public void isValidQuantity(Product product){

        if (product.getAtualQuantity() <= product.getMinimumQuantity()){
            // Send message to RabbitMQ
            String message = "Product ID: " + product.getIdProduct() +
                             ", Name: " + product.getNameProduct() +
                             " reached or exceeded the minimum quantity. Current quantity: " + product.getAtualQuantity();


            rabbitProducer.sendMessage(message);
        }
    }
}
