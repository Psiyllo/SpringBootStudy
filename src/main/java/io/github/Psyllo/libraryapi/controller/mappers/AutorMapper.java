package io.github.Psyllo.libraryapi.controller.mappers;

import io.github.Psyllo.libraryapi.controller.dto.AutorRequestDTO;
import io.github.Psyllo.libraryapi.controller.dto.AutorResponseDTO;
import io.github.Psyllo.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    //@Mapping(source = "nome", target = "nome")
    //Consegue mapear dessa maneira com @Mapping caso a propriedade em DTO seja diferente na entidade
    Autor toEntity (AutorRequestDTO dto);

    AutorResponseDTO toDTO(Autor autor);

}
