package com.RabbitMqConsumer.TesteConsumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class EstoqueConsumer {
    
    @RabbitListener(queues = "ESTOQUE")
    private void consumer(EstoqueDto estoqueDto){
        try {
            System.out.println("Received message: " + estoqueDto);
            System.out.println("Product Code: " + estoqueDto.productCode());
            System.out.println("Product Amount: " + estoqueDto.productAmount());
            System.out.println("-----------------------------------------");
        } catch (Exception e) {
           
        }
    }
}
