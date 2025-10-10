package io.github.Psyllo.libraryapi.controller.mappers;

import io.github.Psyllo.libraryapi.controller.dto.AutorResponseDTO;
import io.github.Psyllo.libraryapi.controller.dto.LivroRequestDTO;
import io.github.Psyllo.libraryapi.controller.dto.LivroResponseDTO;
import io.github.Psyllo.libraryapi.model.GeneroLivro;
import io.github.Psyllo.libraryapi.model.Livro;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-10T14:19:36-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class LivroMapperImpl extends LivroMapper {

    @Autowired
    private AutorMapper autorMapper;

    @Override
    public Livro toEntity(LivroRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Livro livro = new Livro();

        livro.setIsbn( dto.isbn() );
        livro.setTitulo( dto.titulo() );
        livro.setDataPublicacao( dto.dataPublicacao() );
        livro.setGenero( dto.genero() );
        livro.setPreco( dto.preco() );

        livro.setAutor( autorRepository.findById(dto.idAutor()).orElse(null) );

        return livro;
    }

    @Override
    public LivroResponseDTO toDTO(Livro livro) {
        if ( livro == null ) {
            return null;
        }

        UUID id = null;
        String isbn = null;
        String titulo = null;
        LocalDate dataPublicacao = null;
        GeneroLivro genero = null;
        BigDecimal preco = null;
        AutorResponseDTO autor = null;

        id = livro.getId();
        isbn = livro.getIsbn();
        titulo = livro.getTitulo();
        dataPublicacao = livro.getDataPublicacao();
        genero = livro.getGenero();
        preco = livro.getPreco();
        autor = autorMapper.toDTO( livro.getAutor() );

        LivroResponseDTO livroResponseDTO = new LivroResponseDTO( id, isbn, titulo, dataPublicacao, genero, preco, autor );

        return livroResponseDTO;
    }
}
