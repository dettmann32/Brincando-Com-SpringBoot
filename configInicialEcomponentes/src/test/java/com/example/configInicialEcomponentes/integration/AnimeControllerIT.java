package com.example.configInicialEcomponentes.integration;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.configInicialEcomponentes.database.Anime;
import com.example.configInicialEcomponentes.database.AnimeRepository;
import com.example.configInicialEcomponentes.integration.wrapper.PageableResponse;
import com.example.configInicialEcomponentes.util.AnimeCreator;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT /*seta uma porta aletoria */)
@AutoConfigureTestDatabase //configura o database de teste
public class AnimeControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    //Uma forma para pegar a porta que esta rodando
    @LocalServerPort
    private int port;

    @Autowired
    AnimeRepository animeRepository;
    
    @Test
    void listOffPort(){
    ResponseEntity<List<Anime>> animes = new RestTemplate().exchange("http://localhost:"+ port +"/animes/Pageoff", 
    HttpMethod.GET,null, new ParameterizedTypeReference<>(){}); //essa é uma forma, mas recomendaria usar o testRestTemplate

    System.out.println(animes.getBody());

    Assertions.assertThat(animes.getBody()).isNotEmpty();
    }


    @Test
    void listarAnime(){

        animeRepository.save(AnimeCreator.createAnimeToBeSaved());
        animeRepository.save(AnimeCreator.createAnimeToBeSaved("Naruto"));

        ResponseEntity<PageableResponse<Anime>> exchange = testRestTemplate.exchange("http://localhost:"+ port +"/animes", 
        HttpMethod.GET, null, 
        new ParameterizedTypeReference<PageableResponse<Anime>>() {});

        System.out.println(exchange.getBody().toList());

        Assertions.assertThat(exchange.getBody()).isNotEmpty().hasSize(2);
        Assertions.assertThat(exchange.getBody().toList().get(1).getName()).isEqualTo("Naruto");
        


    }

}
