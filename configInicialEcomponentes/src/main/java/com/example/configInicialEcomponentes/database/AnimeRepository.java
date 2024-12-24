package com.example.configInicialEcomponentes.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    
    List<Anime> findByname(String name);
}
