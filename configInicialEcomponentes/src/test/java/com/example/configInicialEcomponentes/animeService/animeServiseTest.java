package com.example.configInicialEcomponentes.animeService;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.configInicialEcomponentes.database.Anime;
import com.example.configInicialEcomponentes.database.AnimeRepository;
import com.example.configInicialEcomponentes.services.animes.animeServise;
import com.example.configInicialEcomponentes.util.AnimeCreator;

@ExtendWith(SpringExtension.class)
public class animeServiseTest {
    @InjectMocks
    private animeServise aServise;

    @Mock(name = "Anime Repository")
    AnimeRepository animeRepositoryMock;

    @Test
    @DisplayName("listarAnime deve retornar uma page de animes")
    void listarAnime_deveRetornarUmaListaDeAnimes() {
        // given
        Page<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.given(animeRepositoryMock.findAll(any(Pageable.class)))
                .willReturn(animePage);

        // when
        Page<Anime> result = aServise.listarAnime(Pageable.unpaged());

        // then
        Assertions.assertThat(result).isNotEmpty()
                .isEqualTo(animePage);

    }

    @Test
    @DisplayName("listarAnimePageoff deve retornar lista de animes")
    void listarAnimePageoff_deveRetornarUmaListaDeAnimes() {
        // given
        List<Anime> animes = List.of(AnimeCreator.createValidAnime(), 
        AnimeCreator.createValidAnime()); 

        BDDMockito.given(animeRepositoryMock.findAll())
        .willReturn(animes);

        //when /then
        Assertions.assertThat(aServise.listarAnimePageoff()).isNotEmpty()
        .isEqualTo(animes).hasSize(2);
    }
}
