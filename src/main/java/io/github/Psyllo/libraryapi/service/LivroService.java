package io.github.Psyllo.libraryapi.service;

import io.github.Psyllo.libraryapi.model.GeneroLivro;
import io.github.Psyllo.libraryapi.model.Livro;
import io.github.Psyllo.libraryapi.repository.LivroRepository;
import io.github.Psyllo.libraryapi.repository.specs.LivroSpecs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.Psyllo.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    @Transactional
    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    @Transactional
    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    @Transactional
    public void deletar(Livro livro){
        repository.delete(livro);
    }

    @Transactional
    //isbn, titulo, nome autor, genero, ano publicacao
    public List<Livro> pesquisa(String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao){

//        Specification<Livro> specs = Specification
//                .where(LivroSpecs.isbnEqual(isbn))
//                .and(LivroSpecs.tituloLike(titulo))
//                .and(LivroSpecs.generoEqual(genero));

        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(isbn != null){
            specs = specs.and(isbnEqual(isbn));
        }

        if(titulo != null){
            specs = specs.and(tituloLike(titulo));
        }

        if(genero != null){
            specs = specs.and(generoEqual(genero));
        }

        if(anoPublicacao != null){
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if(nomeAutor != null){
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        return repository.findAll(specs);
    }

    @Transactional
    public void atualizar(Livro livro){
        if(livro == null){
            throw new RuntimeException("O Livro já precisa estar salvo");
        }
        repository.save(livro);
    }
}
