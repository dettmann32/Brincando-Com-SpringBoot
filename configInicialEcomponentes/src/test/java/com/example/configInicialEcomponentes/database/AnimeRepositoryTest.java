package com.example.configInicialEcomponentes.database;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
public class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persists anime when Successful")
    void save_persistAnime_WhenSuccessful(){
        Anime animeToBeSaved = this.createAnime();
        Anime animeSaved = animeRepository.save(animeToBeSaved); 
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Save updates anime when Successful")
    void put_updateAnime_WhenSuccessful(){
        Anime animeToBeSaved = this.createAnime();
        Anime animeSaved = animeRepository.save(animeToBeSaved); 
        animeSaved.setName("Overlord");
        Anime animeUpdated = animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeUpdated.getName());
    }


    @Test
    @DisplayName("delete remove anime when Successful")
    void delete_removeAnime_WhenSuccessful(){
        Anime animeToBeSaved = this.createAnime();
        Anime animeSaved = animeRepository.save(animeToBeSaved); 
        animeRepository.delete(animeSaved);
        Optional<Anime> anime = animeRepository.findById(animeSaved.getId());
        Assertions.assertThat(anime).isEmpty();
    }

    @Test
    @DisplayName("Find By Name return list of anime when Successful")
    void findByName_returnListOfAnime_WhenSuccessful(){
        animeRepository.save(this.createAnime());
        animeRepository.save(this.createAnime());
        animeRepository.save(this.createAnime("Boku no Hero"));

        Optional<List<Anime>> anime = animeRepository.findByname("Naruto");
        Optional<List<Anime>> anime2 = animeRepository.findByname("Boku no Hero");

        Assertions.assertThat(anime).isNotEmpty();

        Assertions.assertThat(anime.get().get(0).getName())
        .isEqualTo(anime.get().get(1).getName());

        Assertions.assertThat(anime2.get()).isNotEmpty();

        Assertions.assertThat(anime.get().size()).isEqualTo(2);


    }


    @Test
    @DisplayName("Find By Name return empty list when no anime is found")
    void findByname_returnEmpytList_WhenAnimeIsNotFound(){
        animeRepository.save(this.createAnime("Boku no Hero"));
        Optional<List<Anime>> anime = animeRepository.findByname("Naruto");
        Assertions.assertThat(anime.get()).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_throwConstraintViolationException_WhenNameIsEmpty(){
        Anime anime = new Anime();
        Assertions.assertThatThrownBy(() -> animeRepository.save(anime))
        .isInstanceOf(ConstraintViolationException.class)
        .hasMessageContaining("The anime name cannot be empty"); //mensagem que é retornada quando a exceção é lançada (opcional)
        //a tipagem do isInstanceOf é a exceção que é esperada e pode dar dor de cabeça se não for a mesma
        
    }


    private Anime createAnime(){
        return Anime.builder()
                .name("Naruto")
                .build();
    }

    private Anime createAnime(String name){
        return Anime.builder()
                .name(name)
                .build();
    }
}
