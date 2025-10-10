package io.github.Psyllo.libraryapi.controller.mappers;

import io.github.Psyllo.libraryapi.controller.dto.UsuarioRequestDTO;
import io.github.Psyllo.libraryapi.controller.dto.UsuarioResponseDTO;
import io.github.Psyllo.libraryapi.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-10T14:19:36-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setLogin( dto.login() );
        usuario.setSenha( dto.senha() );
        usuario.setEmail( dto.email() );
        List<String> list = dto.roles();
        if ( list != null ) {
            usuario.setRoles( new ArrayList<String>( list ) );
        }

        return usuario;
    }

    @Override
    public UsuarioResponseDTO toDto(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        String login = null;
        String email = null;
        String senha = null;
        List<String> roles = null;

        login = entity.getLogin();
        email = entity.getEmail();
        senha = entity.getSenha();
        List<String> list = entity.getRoles();
        if ( list != null ) {
            roles = new ArrayList<String>( list );
        }

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO( login, email, senha, roles );

        return usuarioResponseDTO;
    }
}
