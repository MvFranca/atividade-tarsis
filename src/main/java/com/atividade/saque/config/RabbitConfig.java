package com.atividade.saque.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${app.rabbit.exchange}")
    private String exchangeName;

    @Value("${app.rabbit.queue}")
    private String queueName;

    @Value("${app.rabbit.routing-key}")
    private String routingKey;

    @Bean
    public TopicExchange saqueExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue saqueQueue() {
        return new Queue(queueName);
    }

    @Bean
    public Binding saqueBinding(Queue saqueQueue, TopicExchange saqueExchange) {
        return BindingBuilder.bind(saqueQueue).to(saqueExchange).with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
