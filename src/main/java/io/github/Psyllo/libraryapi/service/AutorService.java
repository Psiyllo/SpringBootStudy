package io.github.Psyllo.libraryapi.service;

import io.github.Psyllo.libraryapi.Exception.OperacaoNaoPermitidaException;
import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.repository.AutorRepository;
import io.github.Psyllo.libraryapi.repository.LivroRepository;
import io.github.Psyllo.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    private final AutorValidator validator;

    private final LivroRepository livroRepository;

    public Autor salvar (Autor autor){
        validator.validar(autor);
        return autorRepository.save(autor);
    }

    public void atualizar (Autor autor){
        if(autor.getId() == null){
            throw new RuntimeException("O Autor já precisa estar salvo");
        }
        validator.validar(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor){
        if(autorComLivro(autor)){
            throw new OperacaoNaoPermitidaException("O Autor Não Pode Ser Excluido Pois Possui um Livro Cadastrado");
        }
        autorRepository.delete(autor);
    }

    public List<Autor> filtrarAutor (String nome, String nacionalidade){
        if(nome != null && nacionalidade != null){
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }
        if(nome != null){
            return autorRepository.findByNome(nome);
        }
        if(nacionalidade != null){
            return autorRepository.findByNacionalidade(nacionalidade);
        }
        return autorRepository.findAll();
    }
    public boolean autorComLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
