package com.example.demo.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RabbitMqService {
    
    private final RabbitTemplate rabbitTemplate; //dependencia do pacote spring rabbitmq

    public void sendMessageToQueue(String NameQueue, Object Message){

        this.rabbitTemplate.convertAndSend(NameQueue ,Message);
    }

}
