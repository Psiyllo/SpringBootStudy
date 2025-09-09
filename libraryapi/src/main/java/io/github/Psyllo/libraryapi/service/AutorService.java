package io.github.Psyllo.libraryapi.service;

import io.github.Psyllo.libraryapi.Exception.OperacaoNaoPermitidaException;
import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.repository.AutorRepository;
import io.github.Psyllo.libraryapi.repository.LivroRepository;
import io.github.Psyllo.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    @Transactional
    public Autor salvar (Autor autor){
        validator.validar(autor);
        return autorRepository.save(autor);
    }

    @Transactional
    public void atualizar (Autor autor){
        if(autor.getId() == null){
            throw new RuntimeException("O Autor já precisa estar salvo");
        }
        validator.validar(autor);
    }

    @Transactional(readOnly = true)
    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }

    @Transactional
    public void deletar(Autor autor){
        if(autorComLivro(autor)){
            throw new OperacaoNaoPermitidaException("O Autor Não Pode Ser Excluido Pois Possui um Livro Cadastrado");
        }
        autorRepository.delete(autor);
    }

//    @Transactional(readOnly = true)
//    public List<Autor> filtrarAutor (String nome, String nacionalidade){
//        if(nome != null && nacionalidade != null){
//            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
//        }
//        if(nome != null){
//            return autorRepository.findByNome(nome);
//        }
//        if(nacionalidade != null){
//            return autorRepository.findByNacionalidade(nacionalidade);
//        }
//        return autorRepository.findAll();
//    }

    @Transactional
    public List<Autor> filtrarAutorByExample(String nome, String nacionalidade){
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Autor> autorExample = Example.of(autor, matcher);
        return autorRepository.findAll(autorExample);
    }
    public boolean autorComLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
