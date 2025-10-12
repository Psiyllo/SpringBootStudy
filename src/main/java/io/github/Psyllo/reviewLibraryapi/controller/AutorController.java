package io.github.Psyllo.reviewLibraryapi.controller;

import io.github.Psyllo.reviewLibraryapi.model.Autor;
import io.github.Psyllo.reviewLibraryapi.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("autores")
public class AutorController {

    private final AutorService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Autor autor) {
        service.salvar(autor);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listar(){
        return ResponseEntity.ok(service.listar());
    }
}
