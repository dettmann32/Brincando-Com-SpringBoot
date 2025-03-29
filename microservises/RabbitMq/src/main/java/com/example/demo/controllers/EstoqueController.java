package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.rabbitMqConfig.RabbitMqConstants;
import com.example.demo.dto.EstoqueDto;
import com.example.demo.services.RabbitMqService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("estoque")
@AllArgsConstructor
public class EstoqueController {
    
    private final RabbitMqService rabbitMqService;

    @PutMapping
    private ResponseEntity<HttpStatus> updateEstoque(@RequestBody EstoqueDto estoqueDto){
        rabbitMqService.sendMessageToQueue(RabbitMqConstants.QUEUE_ESTOQUE, estoqueDto);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
