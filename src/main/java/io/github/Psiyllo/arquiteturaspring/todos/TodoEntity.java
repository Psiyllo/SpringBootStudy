package io.github.Psiyllo.arquiteturaspring.todos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_table")
public class TodoEntity {

    @Id
    private Integer id;

    private String descricao;

    private Boolean concluido;
}
