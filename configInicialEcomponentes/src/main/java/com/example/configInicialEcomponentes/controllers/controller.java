package com.example.configInicialEcomponentes.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.configInicialEcomponentes.database.Anime;
import com.example.configInicialEcomponentes.services.animes.animeDTO;
import com.example.configInicialEcomponentes.services.animes.animeServise;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController("/")
@AllArgsConstructor
public class controller {
    
    final animeServise aServise;



    //ESSE METODO FOI ALTERADO PARA SUPORTAR PAGINAÇÃO
    //O PAGEABLE É UM OBJETO QUE CONTÉM INFORMAÇÕES SOBRE A PAGINAÇÃO
    //COMO O TAMANHO DA PAGINA, A PAGINA ATUAL, ETC
    //o metodo listarAnime foi alterado para suportar paginação também

    //a paginação pode ser filtrade por parametros na url, da seguinte forma
    //dominio.com/animes?page=0&size=5 por exemplo. com as flags apos o "?"
    @GetMapping("/animes")
    public ResponseEntity<Page<Anime>> listarAnime(Pageable pageable){
        
        return ResponseEntity.ok(aServise.listarAnime(pageable));
    }

    @GetMapping("/animes/Pageoff")
    public ResponseEntity<List<Anime>> listarAnimePageoff(){
        
        return ResponseEntity.ok(aServise.listarAnimePageoff());
    }

    @GetMapping("/animes/{id}")
    public ResponseEntity<Anime> listarId(@PathVariable long id){
        return ResponseEntity.ok(aServise.listarId(id));
    }

    //@RequestParam foi adicionado no commit de requestparam
    //ele substitui o @PathVariable
    //nsse caso a url de uma requisição seria "dominio.com/animes/find?name=<nome do anime>"
    @GetMapping("/animes/find")
    public ResponseEntity<List<Anime>> listarName(@RequestParam String name){
        return ResponseEntity.ok(aServise.listarName(name));
    }

    @PostMapping("/animes")
    public ResponseEntity<Anime> createAnime(@RequestBody @Valid animeDTO anime){
       return ResponseEntity.ok(aServise.createAnime(anime));
    }

    @DeleteMapping(path = "/animes/{id}")
    public ResponseEntity<Void> deletAnime(@PathVariable long id){
        aServise.deletAnime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/animes")
    public ResponseEntity<Void> replace(@RequestBody animeDTO data){
        aServise.replaceAnime(data);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }

    
    
}
