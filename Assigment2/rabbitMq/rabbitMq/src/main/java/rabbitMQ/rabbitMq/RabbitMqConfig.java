package rabbitMQ.rabbitMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {

    @Bean
    public Queue measurementQueue() {
        return new Queue("measurementQueue", true); // Durable queue for measurements
    }

    @Bean
    public Queue deviceChangesQueue() {
        return new Queue("deviceChangesQueue", true); // Durable queue for device changes
    }
    // Define a topic exchange for device changes
    @Bean
    public TopicExchange deviceExchange() {
        return new TopicExchange("device.topic");
    }

    // Bind the deviceChangesQueue to the topic exchange
    @Bean
    public Binding deviceChangesBinding(Queue deviceChangesQueue, TopicExchange deviceExchange) {
        return BindingBuilder.bind(deviceChangesQueue).to(deviceExchange).with("device.change.#");
    }
}