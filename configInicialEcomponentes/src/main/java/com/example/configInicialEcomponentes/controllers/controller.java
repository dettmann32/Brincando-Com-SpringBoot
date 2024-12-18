package com.example.configInicialEcomponentes.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.configInicialEcomponentes.database.Anime;
import com.example.configInicialEcomponentes.services.showSomeThings;
import com.example.configInicialEcomponentes.services.veiculos;
import com.example.configInicialEcomponentes.services.animes.animeDTO;
import com.example.configInicialEcomponentes.services.animes.animeServise;

import lombok.AllArgsConstructor;


@RestController("/")
@AllArgsConstructor
public class controller {
    
    final animeServise aServise;
    final showSomeThings show;

   

    @GetMapping
    public ResponseEntity<List<String>> listar(){
        
        return ResponseEntity.ok(show.listar());

    }

    @GetMapping("/listVei")
    public ResponseEntity<List<veiculos>> listarVeiculos(){
        return ResponseEntity.ok(show.listarVeiculos());
    }

    @GetMapping("/listVei/{id}")
    public ResponseEntity<veiculos> veiculoById(@PathVariable int id){
        return ResponseEntity.ok(show.getById(id));
    }


    @GetMapping("/animes")
    public ResponseEntity<List<Anime>> listarAnime(){
        
        return ResponseEntity.ok(aServise.listarAnime());
    }

    @GetMapping("/animes/{id}")
    public ResponseEntity<Anime> listarId(@PathVariable long id){
        return ResponseEntity.ok(aServise.listarId(id));
    }

    @PostMapping("/animes")
    public ResponseEntity<Anime> createAnime(@RequestBody animeDTO anime){
       return ResponseEntity.ok(aServise.createAnime(anime));
    }
    
}
