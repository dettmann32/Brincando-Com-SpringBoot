package com.example.configInicialEcomponentes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.jdbc.Expectation;
import org.springframework.stereotype.Service;

import com.example.configInicialEcomponentes.database.Anime;
import com.example.configInicialEcomponentes.database.AnimeRepository;

@Service
public class showSomeThings {

    AnimeRepository animeRepository;

    showSomeThings(AnimeRepository anime){
        this.animeRepository = anime;
    }


    private static List<veiculos> veicul;
    
    private List<String> someThing = new ArrayList();
    {
        someThing.add("Carro");
        someThing.add("moto");

        veicul = List.of(new veiculos("Uno"), new veiculos("estrada"),
        new veiculos("biz"),new veiculos("civic"));
    }
  

    public List<String> listar(){
        return someThing;
    } 

    public List<veiculos> listarVeiculos(){
        return veicul;
    }

    public veiculos getById(int id){
        Optional<veiculos> veiculo = veicul.stream()
        .filter(veiculos -> veiculos.getId() == id).findFirst();

        return veiculo.get();
    }


    public List<Anime> listarAnime(){
        return animeRepository.findAll();
    }

    public Anime listarId(Long ids){
        Optional<Anime> opcao = animeRepository.findById(ids);

        return opcao.orElseThrow();
    }
}
