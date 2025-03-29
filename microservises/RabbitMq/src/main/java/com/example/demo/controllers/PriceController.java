package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.rabbitMqConfig.RabbitMqConstants;
import com.example.demo.dto.PriceDto;
import com.example.demo.services.RabbitMqService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("price")
@AllArgsConstructor
public class PriceController {
    
    private final RabbitMqService rabbitMqService;

    @PutMapping
    private ResponseEntity<HttpStatus> updatePrice(@RequestBody PriceDto PriceDto){
        rabbitMqService.sendMessageToQueue(RabbitMqConstants.QUEUE_PRICE, PriceDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
