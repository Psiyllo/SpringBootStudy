package io.github.Psyllo.reviewLibraryapi.Controller;

import io.github.Psyllo.reviewLibraryapi.Model.Autor;
import io.github.Psyllo.reviewLibraryapi.Service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
