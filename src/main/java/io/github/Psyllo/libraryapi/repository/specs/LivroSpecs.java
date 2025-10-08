package io.github.Psyllo.libraryapi.repository.specs;

import io.github.Psyllo.libraryapi.model.GeneroLivro;
import io.github.Psyllo.libraryapi.model.Livro;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    public static Specification<Livro> isbnEqual(String isbn){
        //where isbn = :isbn
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo){
        //utilizando cb.upper e titulo.toUpperCase para comparar ambos em UpperCase desconsiderando a case
        return(root, query, cb) -> cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Livro> generoEqual(GeneroLivro genero){
        return (root, query, cb) -> cb.equal(root.get("genero"), genero);
    }

    //ad to_char(data_publicacao, 'YYYY') from livro = :anoPublicacao
    public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao){
        return (root, query, cb) -> cb.equal(
                cb.function("to_char", String.class,
                        root.get("dataPublicacao"), cb.literal("YYYY")) ,anoPublicacao.toString());
    }
}
