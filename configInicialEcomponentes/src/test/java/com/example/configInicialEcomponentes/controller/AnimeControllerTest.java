package com.example.configInicialEcomponentes.controller;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.configInicialEcomponentes.controller.util.AnimeCreator;
import com.example.configInicialEcomponentes.controllers.controller;
import com.example.configInicialEcomponentes.database.Anime;
import com.example.configInicialEcomponentes.services.animes.animeServise;

//essa anotation serve para o teste rodar com o spring	
//porem sem subir o servidor
//uma alternavita para essa anotation seria usar a anotation @SpringBootTest que sobe o servidor
@ExtendWith(SpringExtension.class) //alternativa para @SpringBootTest
public class AnimeControllerTest {

    @InjectMocks //injeta uma classe mockada
    private controller controller;

    @Mock  //cria uma classe mockada. Serve para alterar o comportamento de uma classe que esta sendo usada por uma classe injetada
    private animeServise aServiseMock;


    @BeforeEach //executa antes de cada teste
    void setUp(){

      //lista de animes
       Page<Anime> ValidAnimePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
       
       //@Deprecated
       //BDDMockito.when(aServiseMock.listarAnime(ArgumentMatchers.any()/*ignora qualquer argumento*/ ))
       //.thenReturn(ValidAnimePage); //o mock não altera a tiapagem de retorno do metodo original.

        BDDMockito.given(aServiseMock.listarAnime(any() /*ignora qualquer argumento (pode ser tipado colocando "classeDoTipo.class" entre os "()")*/))
        .willReturn(ValidAnimePage);//o mock não altera a tiapagem de retorno do metodo original.
  

      //listarId
      Anime anime = AnimeCreator.createValidAnime();

      BDDMockito.when(aServiseMock.listarId(ArgumentMatchers.any()))
      .thenReturn(anime);

    }
    
    @Test
    @DisplayName("listarAnime retorna uma pagina de animes quando bem sucedido")
    void listarAnime_ReturnPageOfAnime_WhenSucceful(){
      //o objetivo desse teste é verificar se o metodo listarAnime do controller esta retornando uma pagina de animes
       String expectedName = AnimeCreator.createValidAnime().getName();
       
       Page<Anime> animePage = controller.listarAnime(null).getBody();

       Assertions.assertThat(animePage).isNotNull();
       Assertions.assertThat(animePage.toList()).isNotEmpty();
       Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);

    }



    @Test
    @DisplayName("listarAnimeById retorna um anime quando bem sucedido")
    void listarAnimeById_ReturnOfAnime_WhenSucceful(){
      //o objetivo desse teste é verificar se o metodo listarAnime do controller esta retornando uma pagina de animes
       Anime animeExpected = AnimeCreator.createValidAnime();
       
       ResponseEntity<Anime> anime = controller.listarId(1);

       Assertions.assertThat(anime).isNotNull();
       Assertions.assertThat(anime.getBody()).isEqualTo(animeExpected);

    }
}
