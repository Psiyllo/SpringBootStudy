package io.github.Psyllo.reviewLibraryapi.service;

import io.github.Psyllo.reviewLibraryapi.model.Autor;
import io.github.Psyllo.reviewLibraryapi.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;

    public void salvar(Autor autor) {
        repository.save(autor);
    }

    public List<Autor> listar (){
        return repository.findAll();
    }
}
