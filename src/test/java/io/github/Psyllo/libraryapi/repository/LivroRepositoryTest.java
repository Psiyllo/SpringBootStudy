package io.github.Psyllo.libraryapi.repository;

import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.model.GeneroLivro;
import io.github.Psyllo.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("901212-23213");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setTitulo("LOM");
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setDataPublicacao(LocalDate.of(2013, 8, 28));

        Autor autor = autorRepository
                .findById(UUID.fromString("612668f2-0577-4202-a902-cab7775338ca"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("901212-23213");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setTitulo("Cientifica top");
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setDataPublicacao(LocalDate.of(2013, 8, 28));

        Autor autor = new Autor();
        autor.setNome("rafael");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2001, 6, 14));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("901212-23213");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setTitulo("LOM");
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setDataPublicacao(LocalDate.of(2013, 8, 28));

        Autor autor = new Autor();
        autor.setNome("Paulo");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2004, 6, 14));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("ba9ec9c8-eb5d-40f5-8bf9-7815a6a8faca");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("612668f2-0577-4202-a902-cab7775338ca");
        Autor Eduarda = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(Eduarda);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("ba9ec9c8-eb5d-40f5-8bf9-7815a6a8faca");
        livroRepository.deleteById(id);
    }

    @Test
    void deletarCascade() {
        UUID id = UUID.fromString("ba9ec9c8-eb5d-40f5-8bf9-7815a6a8faca");
        livroRepository.deleteById(id);
        //Se habilitar o cascade na model isso ira deletar o livro e também o autor do livro.
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("84e65d7d-00be-4ccc-9284-9e1e4fec39db");
        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor :");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("3407bc7c-5b7b-40a1-9407-ff1453f6f645");
        var autor = autorRepository.findById(id).get();

        List<Livro> livrosListar = livroRepository.findByAutor(autor);
        autor.setLivros(livrosListar);

        autor.getLivros().forEach(System.out::println);
    }

    @Test
    void listarLivrosTitulo(){
        List<Livro> lista = livroRepository.findByTitulo("genshin impact lixo");
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosIsbn(){
        List<Livro> listaIsbn = livroRepository.findByIsbn("415342-93243");
        listaIsbn.forEach(System.out::println);
    }

    @Test
    void listarLivrosTituloAndPreco(){
        var preco = BigDecimal.valueOf(2.00);
        var titulo = "genshin impact lixo";

        List<Livro> listaTituloAndPreco = livroRepository.findByTituloAndPreco(titulo, preco);
        listaTituloAndPreco.forEach(System.out::println);
    }

}