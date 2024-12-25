package com.example.exeptions.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.exeptions.BadRequestExeption;
import com.example.exeptions.BadRequestExeptionsDetails;


//ESSA ANOTAÇÃO SERVE PARA OS CONTROLLERS OBSERVAREM O CODIGO QUE ESTA DENTRO DESSA CLASSE
//nesse caso ele funciona parecido com flags
@ControllerAdvice
public class RestExeptionHandler{
    
    //essa anotation diz ao controller para usar esse metodo como handler 
    @ExceptionHandler(BadRequestExeption.class)
    public ResponseEntity<BadRequestExeptionsDetails> handlerBadResquestExeption(BadRequestExeption bre){
        return new ResponseEntity<>(BadRequestExeptionsDetails.builder()      
            .timestemp(LocalDateTime.now())
            .satatus(HttpStatus.BAD_REQUEST.value())
            .title("Bad request exeption")
            .details(bre.getMessage())
            .developerMessage(bre.getClass().getName())
        .build(), HttpStatus.BAD_REQUEST);
    }


}