package com.example.configInicialEcomponentes.services;

import java.util.concurrent.ThreadLocalRandom;

public class veiculos {
    private int id;

    private String nome;

    public veiculos(String nome) {
        this.id = ThreadLocalRandom.current().nextInt(1,1000);
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
