package io.github.Psyllo.libraryapi.controller;

import io.github.Psyllo.libraryapi.controller.dto.UsuarioRequestDTO;
import io.github.Psyllo.libraryapi.controller.mappers.UsuarioMapper;
import io.github.Psyllo.libraryapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid UsuarioRequestDTO dto){
        var usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }
}
