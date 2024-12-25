package com.example.exeptions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BadRequestExeptionsDetails {
    private String title;
    private int satatus;
    private String details;
    private String developerMessage;
    private LocalDateTime timestemp;
}
