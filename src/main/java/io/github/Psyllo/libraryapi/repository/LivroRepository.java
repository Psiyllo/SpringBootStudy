package io.github.Psyllo.libraryapi.repository;

import io.github.Psyllo.libraryapi.model.Autor;
import io.github.Psyllo.libraryapi.model.GeneroLivro;
import io.github.Psyllo.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */
public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method
    //select * from livro where id_autor = id
    List<Livro> findByAutor(Autor auto);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //select * from livro where inicio=? and fim=?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    //select * from livro where titulo like= ?
    List<Livro> findByTituloLike(String Titulo);

    //JPQL referencia entidade e propriedade
    //select l.* from Livro as l order by l.titulo, l.preco
    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosLivrosOrdenadosTituloAndPreco();

    //select a.*, l.titulo from livro l join autor a on a.id = l.id_autor
    @Query(" select a from Livro l join l.autor a")
    List<Autor> listarAutoresDosLivros();

    @Query(" select distinct l.titulo from Livro l order by l.titulo")
    List<String> listarLivrosDiferentes();

    @Query("""
            select distinct l.genero from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
    """)
    List<String> listarGeneroQuandoNacionalidadeBrasileira();

    public interface LivroTituloPreco{
        String getTitulo();
        BigDecimal getPreco();
    }

    @Query(" select l.titulo, l.preco from Livro l order by l.titulo")
    List<LivroTituloPreco> listarTituloAndPreco();

    //named parameters -> parametros nomeados
    @Query(" select l from Livro l where l.genero = :genero order by :nomePropriedade")
    List<Livro> findByGenero(
            @Param("genero") GeneroLivro generoLivro,
            @Param("nomePropriedade") String nomePropriedade);

    //positional parameters
    @Query(" select l from Livro l where l.genero = ?1 order by ?2")
    List<Livro> findByGeneroPositionalParameters(GeneroLivro generoLivro, String nomePropriedade);
}