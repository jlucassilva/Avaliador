package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Competencia implements Serializable {
    private static final long serialVersionUID = -2449149862024727141L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native")
    private Long id;

    @Column
    private String descricao;

    public Competencia() {
    }

    public Competencia(Competencia competencia) {
        this.id = competencia.id;
        this.descricao = competencia.descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
