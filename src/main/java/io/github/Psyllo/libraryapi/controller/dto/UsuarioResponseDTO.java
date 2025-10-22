package io.github.Psyllo.libraryapi.controller.dto;

import java.util.List;

public record UsuarioResponseDTO(
        String login,
        String email,
        String senha,
        List<String> roles) {

}
