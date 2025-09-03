package io.github.Psyllo.libraryapi.controller;

import io.github.Psyllo.libraryapi.controller.dto.AutorRequestDTO;
import io.github.Psyllo.libraryapi.controller.dto.AutorResponseDTO;
import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("autores")
//http://localhost:8080/autores
public class AutorController{

    private final AutorService service;

    public AutorController(AutorService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorRequestDTO autor){
        Autor autorEntidade = autor.mapearParaAutor();
        service.salvar(autorEntidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorResponseDTO> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);
        if(autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorResponseDTO autorResponseDTO = new AutorResponseDTO(autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade());
            return ResponseEntity.ok(autorResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        if(autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }
}
