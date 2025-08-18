package io.github.Psyllo.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Getter
@Setter
public class Livro {

    @Id
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Column(name = "genero", length = 30, nullable = false)
    private String genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private Double preco;
//    private BigDecimal preco;       BigDecimal melhor para valores monetários

    @ManyToOne
    @JoinColumn (name = "id_autor")
    private Autor autor;

}
