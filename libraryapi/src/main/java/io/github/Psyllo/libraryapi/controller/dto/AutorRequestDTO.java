package io.github.Psyllo.libraryapi.controller.dto;

import io.github.Psyllo.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AutorRequestDTO(
        @NotBlank(message = "Campo Obrigatório")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
        String nome,
        @NotNull(message = "Campo Obrigatório")
        @Past(message = "Não pode ser uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo Obrigatório")
        @Size(min = 2, max = 50, message = "Campo fora do tamanho padrão")
        String nacionalidade) {

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
