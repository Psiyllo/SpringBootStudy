package io.github.Psyllo.libraryapi.controller.dto;

import java.util.List;

public record UsuarioRequestDTO(
        String login,
        String senha,
        List<String> roles) {
}
