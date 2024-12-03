package com.example.MonitoringAndCommunicationMicroService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue measurementQueue() {
        return new Queue("measurementQueue", true);
    }

    @Bean
    public Queue deviceChangesQueue() {
        return new Queue("deviceChangesQueue", true);
    }

    @Bean
    public TopicExchange deviceExchange() {
        return new TopicExchange("device.topic");
    }

    @Bean
    public Binding deviceChangesBinding(Queue deviceChangesQueue, TopicExchange deviceExchange) {
        return BindingBuilder.bind(deviceChangesQueue).to(deviceExchange).with("device.change.#");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}
