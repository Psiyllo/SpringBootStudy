package io.github.Psyllo.libraryapi.repository;

import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.model.GeneroLivro;
import io.github.Psyllo.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("901212-23213");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setTitulo("LOM");
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setDataPublicacao(LocalDate.of(2013,8,28));

        Autor autor = autorRepository
                .findById(UUID.fromString("612668f2-0577-4202-a902-cab7775338ca"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }
}