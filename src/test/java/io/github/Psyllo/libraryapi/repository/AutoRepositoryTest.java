package io.github.Psyllo.libraryapi.repository;

import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.model.GeneroLivro;
import io.github.Psyllo.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutoRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

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

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("d253c6f4-5652-4d60-bf4d-deee2c463441");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("6fff7d3b-71e1-4402-b6bf-0328a32b3cee");
        var paulo = repository.findById(id).get();
        repository.delete(paulo);
    }
    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Jonathan Rodrigues dos Reis");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2004, 1, 30));

        Livro livro = new Livro();
        livro.setIsbn("702312-67843");
        livro.setPreco(BigDecimal.valueOf(100000000));
        livro.setTitulo("Wunthering Waves no Godot");
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setDataPublicacao(LocalDate.of(2025, 8, 22));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("415342-93243");
        livro2.setPreco(BigDecimal.valueOf(2));
        livro2.setTitulo("genshin impact lixo");
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setDataPublicacao(LocalDate.of(2025, 8, 22));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }
}
