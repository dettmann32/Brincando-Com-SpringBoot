package com.example.configInicialEcomponentes.exeptions;

import java.time.LocalDateTime;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ExeptionDetails {
    protected String title;
    protected int satatus;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestemp;
}
