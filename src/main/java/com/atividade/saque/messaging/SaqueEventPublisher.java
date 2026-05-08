package com.atividade.saque.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SaqueEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public SaqueEventPublisher(
            RabbitTemplate rabbitTemplate,
            @Value("${app.rabbit.exchange}") String exchangeName,
            @Value("${app.rabbit.routing-key}") String routingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void publicar(SaqueRealizadoEvent event) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
    }
}
