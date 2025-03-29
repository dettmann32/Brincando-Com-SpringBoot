package com.RabbitMqConsumer.TesteConsumer;

import org.springframework.amqp.support.converter.AllowedListDeserializingMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    public MessageConverter messageConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        if (converter instanceof AllowedListDeserializingMessageConverter) {
            ((AllowedListDeserializingMessageConverter) converter)
                .addAllowedListPatterns("com.example.demo.dto.*");
        }
        return converter;
    }

}