package com.rfid.consumer.rfidconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  public Queue configQueue() {
    return new Queue("rfid-queue", true);
  }

  @Bean
  DirectExchange exchange() {
    return new DirectExchange("direct-exchange");
  }

  @Bean
  Binding configBinding(Queue configQueue, DirectExchange exchange) {
    return BindingBuilder.bind(configQueue).to(exchange).with("rfid-routing-key");
  }


}