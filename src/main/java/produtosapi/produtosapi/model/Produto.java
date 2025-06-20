package produtosapi.produtosapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")
//também opcional assim como column (mesma regra)
public class Produto {

    //@Column nesse caso serviria caso no banco a primarykey estivesse como "codigo"
    //Serve para mapear as colunas do banco para entidade
    //@Column(name = "codigo")
    @Id
     private String id;
     private String nome;
     private String descricao;
     private Double preco;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                '}';
    }
}
