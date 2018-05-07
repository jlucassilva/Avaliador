package model;

import java.time.LocalDateTime;
import java.util.List;

public class Vaga {
    private Long id;
    private String titulo;
    private String descricao;
    private double salario;
    private LocalDateTime dataDoAnucio;
    private Anunciante anunciante;
    private List<Competencia> competencias;

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

    public LocalDateTime getDataDoAnucio() {
        return dataDoAnucio;
    }

    public void setDataDoAnucio(LocalDateTime dataDoAnucio) {
        this.dataDoAnucio = dataDoAnucio;
    }

    public Anunciante getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }
}
