package com.example.configInicialEcomponentes.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExeption extends RuntimeException{
    
    public BadRequestExeption(String message){
        super(message);
    }
}