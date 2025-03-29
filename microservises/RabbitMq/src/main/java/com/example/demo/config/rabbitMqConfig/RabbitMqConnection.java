package com.example.demo.config.rabbitMqConfig;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.stereotype.Component;



import jakarta.annotation.PostConstruct;

@Component
public class RabbitMqConnection {
    
    private static final String EXCHANGE_NAME = "amq.direct";
    private final AmqpAdmin amqpAdmin;

    RabbitMqConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    @PostConstruct // essa anotação faz com que metodos sejam executados logo após a classe ser instanciada
    private void addNewQueue(){
        
        Queue QueueEstoque = this.CreateQueue("PRICE");
        Queue QueuePrice = this.CreateQueue("PRICE");

        DirectExchange exchange = this.CreateDirectExchange();

        Binding EstoqueRelationship = this.BindingRelationshipBetweenQueueAndExchange(QueueEstoque, exchange);
        Binding PriceRelationship = this.BindingRelationshipBetweenQueueAndExchange(QueuePrice, exchange);

        this.amqpAdmin.declareQueue(QueueEstoque);
        this.amqpAdmin.declareQueue(QueuePrice);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(EstoqueRelationship);
        this.amqpAdmin.declareBinding(PriceRelationship);
    }

    private Queue CreateQueue(String QueueName){
        return new Queue(QueueName, true, false, false);
    } 

    private DirectExchange CreateDirectExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding BindingRelationshipBetweenQueueAndExchange(Queue queue, DirectExchange directExchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE,
         directExchange.getName(), queue.getName(), null);
    }

}
