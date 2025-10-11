package io.github.Psyllo.reviewLibraryapi.Service;

import io.github.Psyllo.reviewLibraryapi.Model.Autor;
import io.github.Psyllo.reviewLibraryapi.Repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;

    public void salvar(Autor autor) {
        repository.save(autor);
    }
}
