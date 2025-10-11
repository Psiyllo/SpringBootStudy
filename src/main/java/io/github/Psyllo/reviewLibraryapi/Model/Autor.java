package io.github.Psyllo.reviewLibraryapi.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "autor")
@Data
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    @Column(name = "nacionalidade")
    private String Nacionalidade;
}
