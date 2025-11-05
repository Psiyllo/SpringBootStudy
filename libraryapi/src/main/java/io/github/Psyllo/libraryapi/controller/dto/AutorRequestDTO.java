package io.github.Psyllo.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(name = "AutorRequest")
public record AutorRequestDTO(
        @NotBlank(message = "Campo Obrigatório")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
        @Schema(name = "nome")
        String nome,
        @NotNull(message = "Campo Obrigatório")
        @Past(message = "Não pode ser uma data futura")
        @Schema(name = "dataNascimento")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo Obrigatório")
        @Size(min = 2, max = 50, message = "Campo fora do tamanho padrão")
        @Schema(name = "nacionalidade")
        String nacionalidade) {
}
