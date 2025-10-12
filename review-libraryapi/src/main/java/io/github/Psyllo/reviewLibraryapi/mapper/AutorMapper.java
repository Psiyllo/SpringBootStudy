package io.github.Psyllo.reviewLibraryapi.mapper;

import io.github.Psyllo.reviewLibraryapi.dto.AutorRequestDTO;
import io.github.Psyllo.reviewLibraryapi.dto.AutorResponseDTO;
import io.github.Psyllo.reviewLibraryapi.model.Autor;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    AutorResponseDTO toDto(Autor autor);

    Autor toEntity(AutorRequestDTO AutorRequestDTO);
}
