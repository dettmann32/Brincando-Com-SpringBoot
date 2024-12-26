package com.example.clientExemple;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.configInicialEcomponentes.database.Anime;




public class client {
    
    //ESSA THREAD É UM CLIENTE QUE FAZ REQUISIÇÕES PARA O SERVIDOR
    public static void main(String[] args) {

        //essa requisição é feita ao servidor, e retorna um objeto do tipo Anime e os metadados da requisição
        ResponseEntity<Anime> anime = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class,6);
        System.out.println(anime);

        //essa requisição é feita ao servidor, e retorna um objeto do tipo Anime
        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class,6 );
        System.out.println(object);

        //essa requisição é feita ao servidor, e retorna uma lista de objetos do tipo Anime
        ResponseEntity<List<Anime>> animePage = new RestTemplate().exchange("http://localhost:8080/animes/Pageoff", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>(){});
        System.out.println(animePage.getBody());

        //essa requisição é feita ao servidor, e salma um objeto do tipo Anime no banco de dados
        Anime kingdom = Anime.builder().name("kingdom").build();
        ResponseEntity<Anime> kingsomAnime = new RestTemplate().exchange("http://localhost:8080/animes", 
        HttpMethod.POST, 
        new HttpEntity<>(kingdom), 
        Anime.class);
        System.out.println(kingsomAnime.getBody());
    }
}
