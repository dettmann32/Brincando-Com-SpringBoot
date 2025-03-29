package com.RabbitMqConsumer.TesteConsumer.consumer;

import java.io.Serializable;

public record EstoqueDto(int productCode, int productAmount) implements Serializable {
};