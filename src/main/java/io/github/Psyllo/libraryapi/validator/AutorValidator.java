package io.github.Psyllo.libraryapi.validator;

import io.github.Psyllo.libraryapi.Exception.RegistroDuplicadoException;
import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor){
        if(existeAutorCadastrado(autor)){
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        var nome = autor.getNome();
        var data = autor.getDataNascimento();
        var nac  = autor.getNacionalidade();
        if (nome == null || data == null || nac == null) {
            return false;
        }

        // POST: sem id -> existe alguém com os mesmos dados ?
        if (autor.getId() == null) {
            return repository.existsByNomeAndDataNascimentoAndNacionalidade(nome, data, nac);
        }

        // PUT: com id -> existe ALGUM OUTRO registro com os mesmos dados?
        return repository.existsByNomeAndDataNascimentoAndNacionalidadeAndIdNot(nome, data, nac, autor.getId());
    }
}
