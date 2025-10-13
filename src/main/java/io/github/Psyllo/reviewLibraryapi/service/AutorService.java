package io.github.Psyllo.reviewLibraryapi.service;

import io.github.Psyllo.reviewLibraryapi.model.Autor;
import io.github.Psyllo.reviewLibraryapi.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void excluir(Autor autor) {
        repository.delete(autor);
    }

    public void atualizar(Autor autor) {
        repository.save(autor);
    }
}
