package io.github.Psyllo.reviewLibraryapi.controller;

import io.github.Psyllo.reviewLibraryapi.dto.AutorResponseDTO;
import io.github.Psyllo.reviewLibraryapi.mapper.AutorMapper;
import io.github.Psyllo.reviewLibraryapi.model.Autor;
import io.github.Psyllo.reviewLibraryapi.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("autores")
public class AutorController {

    private final AutorService service;
    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Autor autor) {
        service.salvar(autor);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listar() {
        List<Autor> listar = service.listar();
        List<AutorResponseDTO> lista = listar.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok().body(lista);
    }
}
