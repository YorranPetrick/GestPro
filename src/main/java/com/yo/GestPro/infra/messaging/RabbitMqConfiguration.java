package com.yo.GestPro.infra.messaging;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Date;

@Configuration
public class RabbitMqConfiguration {

    @Bean
    //Declare Queues
    public Queue queue(){
        return new Queue("gestPro.products.queue", true);
    }

    @Bean
    //Declare Exchange
    public TopicExchange exchange(){
        return new TopicExchange("gestPro.exchange");
    }

    @Bean
    //Bind Queue to Exchange with Routing Key
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("gestPro.products.routingKey"
        );
    }

    //Return Message Properties for Producer
    @Bean
    public MessagePostProcessor messageProperties(){

        Date now = Date.from(Instant.now());

        return message -> {
            message.getMessageProperties().setTimestamp(now);
            message.getMessageProperties().setContentType("text/plain");
            message.getMessageProperties().setPriority(0);
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);

            return message;
        };
    }
}
