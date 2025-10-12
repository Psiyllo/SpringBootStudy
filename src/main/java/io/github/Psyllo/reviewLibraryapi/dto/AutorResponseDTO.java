package io.github.Psyllo.reviewLibraryapi.dto;
import java.time.LocalDate;
import java.util.UUID;

public record AutorResponseDTO (
    UUID id,
    String nome,
    LocalDate dataNascimento,
    String nacionalidade){
}
