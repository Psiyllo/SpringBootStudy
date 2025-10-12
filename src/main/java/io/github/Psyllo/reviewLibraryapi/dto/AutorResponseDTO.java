package io.github.Psyllo.reviewLibraryapi.dto;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AutorResponseDTO {

    private String nome;

    private LocalDate dataNascimento;

    private String nacionalidade;
}
