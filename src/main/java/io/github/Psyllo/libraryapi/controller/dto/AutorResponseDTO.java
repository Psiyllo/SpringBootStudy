package io.github.Psyllo.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorResponseDTO(
        UUID id,
        @NotBlank(message = "Campo Obrigatório")
        String nome,
        @NotNull(message = "Campo Obrigatório")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo Obrigatório")
        String nacionalidade) {
}