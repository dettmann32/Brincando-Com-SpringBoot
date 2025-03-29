package com.RabbitMqConsumer.TesteConsumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.dto.EstoqueDto;



@Component
public class EstoqueConsumer {
    
    @RabbitListener(queues = "ESTOQUE")
    private void consumer(EstoqueDto estoqueDto){
        
            System.out.println("Received message: " + estoqueDto);
            System.out.println("Product Code: " + estoqueDto.getProductCode());
            System.out.println("Product Amount: " + estoqueDto.getProductAmount());
            System.out.println("-----------------------------------------");
    }
}
