package com.example.configInicialEcomponentes.util;

import com.example.configInicialEcomponentes.database.Anime;

public class AnimeCreator {
    
    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }

    public static Anime createAnimeToBeSaved(String name){
        return Anime.builder()
                .name(name)
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .name("Hajime no Ippo")
                .id(1L)
                .build();
    }

    public static Anime createValidAnime(String name){
        return Anime.builder()
                .name(name)
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .name("Hajime no Ippo 2")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime(String name){
        return Anime.builder()
                .name(name + "Updated")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime(String name, long id){
        return Anime.builder()
                .name(name + "Updated")
                .id(id)
                .build();
    }
}

