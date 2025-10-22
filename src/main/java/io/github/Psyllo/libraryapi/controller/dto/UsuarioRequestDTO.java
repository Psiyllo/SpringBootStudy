package io.github.Psyllo.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioRequestDTO(
        @NotBlank(message = "campo obrigatorio")
        String login,
        @Email(message = "invalido")
        @NotBlank(message = "campo obrigatorio")
        String email,
        @NotBlank(message = "campo obrigatorio")
        String senha,
        List<String> roles) {
}
