package io.github.Psyllo.libraryapi.controller.mappers;

import io.github.Psyllo.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.Psyllo.libraryapi.model.Livro;
import io.github.Psyllo.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO dto);

}
