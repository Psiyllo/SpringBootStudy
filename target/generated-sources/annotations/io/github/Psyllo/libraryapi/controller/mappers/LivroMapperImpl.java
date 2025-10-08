package io.github.Psyllo.libraryapi.controller.mappers;

import io.github.Psyllo.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.Psyllo.libraryapi.model.Livro;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-08T10:15:54-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class LivroMapperImpl extends LivroMapper {

    @Override
    public Livro toEntity(CadastroLivroDTO dto) {
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
}
