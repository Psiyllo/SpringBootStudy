package io.github.Psyllo.libraryapi.controller;

import io.github.Psyllo.libraryapi.Exception.OperacaoNaoPermitidaException;
import io.github.Psyllo.libraryapi.Exception.RegistroDuplicadoException;
import io.github.Psyllo.libraryapi.controller.dto.AutorRequestDTO;
import io.github.Psyllo.libraryapi.controller.dto.AutorResponseDTO;
import io.github.Psyllo.libraryapi.controller.dto.ErroResposta;
import io.github.Psyllo.libraryapi.controller.mappers.AutorMapper;
import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.repository.AutorRepository;
import io.github.Psyllo.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores")
@RequiredArgsConstructor
//http://localhost:8080/autores
public class AutorController{

    private final AutorService service;

    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorRequestDTO dto){
        try {
            Autor autor = mapper.toEntity(dto);
            service.salvar(autor);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autor.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoException e){
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorResponseDTO> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = service.obterPorId(idAutor);

        return service
                .obterPorId(idAutor)
                .map(autor -> {
                    AutorResponseDTO dto = mapper.toDTO(autor);
                    return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletarAutor(@PathVariable("id") String id){
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deletar(autorOptional.get());
            return ResponseEntity.noContent().build();
        }catch (OperacaoNaoPermitidaException e){
            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);

        }
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> pesquisarAutor(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade){

        List<Autor> resultado = service.filtrarAutorByExample(nome, nacionalidade);
        List<AutorResponseDTO> lista = resultado.stream().map(mapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarAutor(@PathVariable("id") String id,
        @RequestBody AutorRequestDTO dto){
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var autor = autorOptional.get();
            autor.setNome(dto.nome());
            autor.setDataNascimento(dto.dataNascimento());
            autor.setNacionalidade(dto.nacionalidade());

            service.atualizar(autor);

            return ResponseEntity.noContent().build();
        }catch (RegistroDuplicadoException e){
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }
}
