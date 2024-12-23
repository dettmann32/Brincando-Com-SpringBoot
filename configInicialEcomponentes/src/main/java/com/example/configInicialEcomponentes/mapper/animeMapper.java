package com.example.configInicialEcomponentes.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.configInicialEcomponentes.database.Anime;
import com.example.configInicialEcomponentes.services.animes.animeDTO;

// essa classe serve para fazer mapeamento de dados em um objeto
//na pratica ele substitui um builder

@Mapper
public abstract class animeMapper {

    //isso é para nós termos acesso a instancia
    public static final animeMapper intanse = Mappers.getMapper(animeMapper.class);

    //isso é para configurar o mapeamento do framework
    public abstract Anime toAnime(animeDTO anime);
    
}
