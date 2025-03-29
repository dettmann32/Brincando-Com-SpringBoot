package com.example.demo.dto;

import java.io.Serializable;
                                //É nescessário a implementação dessa interface para o spring
                                //converter os dados para um padrão apropriado para o RabbitMq
public record EstoqueDto(int productCode, double productAmount) implements Serializable {
    
    public int getProductCode() {
        return productCode;
    }

    public double getProductAmount() {
        return productAmount;
    }
}
