package com.example.demo.dto;

import java.io.Serializable;
                                                            //checar o dto de estoque
public record PriceDto(int ProductCode, double ProductPrice) implements Serializable{
  
}
