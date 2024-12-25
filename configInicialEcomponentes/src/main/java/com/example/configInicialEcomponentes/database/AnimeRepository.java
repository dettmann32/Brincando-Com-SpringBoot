package com.example.configInicialEcomponentes.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    
    Optional<List<Anime>> findByname(String name);

    
}
