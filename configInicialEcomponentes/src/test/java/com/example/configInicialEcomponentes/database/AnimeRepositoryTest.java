package com.example.configInicialEcomponentes.database;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
public class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    void save_persistAnime_WhenSuccessful(){
        Anime animeToBeSaved = this.createAnime();
        Anime animeSaved = animeRepository.save(animeToBeSaved); 
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    private Anime createAnime(){
        return Anime.builder()
                .name("Naruto")
                .build();
    }
}
