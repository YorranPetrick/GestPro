package com.yo.GestPro.infra.messaging;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class RabbitProducer {

    final RabbitMqConfiguration rabbitMqConfiguration;
    final AmqpTemplate amqpTemplate;

    public void sendMessage(String message) {

        amqpTemplate.convertAndSend(
                "gestPro.exchange",  // Exchange
                "gestPro.products.routingKey",  // Key
                message,
                rabbitMqConfiguration.messageProperties()
        );
    }
}
