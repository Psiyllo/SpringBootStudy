package io.github.Psyllo.libraryapi.service;

import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.model.GeneroLivro;
import io.github.Psyllo.libraryapi.model.Livro;
import io.github.Psyllo.libraryapi.repository.AutorRepository;
import io.github.Psyllo.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Transactional
    public void executar(){

        Autor autor = new Autor();
        autor.setNome("Wendhel1");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2001, 6, 14));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("901212-23213");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setTitulo("Livro do Wendhel");
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setDataPublicacao(LocalDate.of(2002, 8, 28));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Wendhel1")){
            throw new RuntimeException("Deu erro amigo");
        }
    }
}
