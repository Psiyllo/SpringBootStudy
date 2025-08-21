package io.github.Psyllo.libraryapi.repository;

import io.github.Psyllo.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutoRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Eduarda");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2025, 7, 28));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("d253c6f4-5652-4d60-bf4d-deee2c463441");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1970, 9, 21));

            repository.save(autorEncontrado);

        }
    }
}
