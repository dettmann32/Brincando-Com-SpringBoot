package com.example.configInicialEcomponentes.services.animes;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record animeDTO(Long id, @NotEmpty @NotNull String name) {
}
