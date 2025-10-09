package io.github.Psyllo.libraryapi.controller.mappers;

import ch.qos.logback.core.model.ComponentModel;
import io.github.Psyllo.libraryapi.controller.dto.UsuarioRequestDTO;
import io.github.Psyllo.libraryapi.controller.dto.UsuarioResponseDTO;
import io.github.Psyllo.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toDto(Usuario entity);

}
