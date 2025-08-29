package io.github.Psyllo.libraryapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data // = @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "autor")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;

    @ManyToOne(
//            cascade = CascadeType.ALL
        fetch = FetchType.LAZY //Por padrão todos os relacionamentos terminados em ToOne são FetchType.EAGER que significa que sempre irá buscar os dados do relacionamento, no caso sempre irá buscar autor;
            //Lazy só ira disparar um SQL quando buscar algum atributo e precisa estar denntro de um @transactional. exemplo em LivroRepositoryTest(especificamente em buscarLivroTest)
    )
    @JoinColumn (name = "id_autor")
    private Autor autor;

}
