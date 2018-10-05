package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Avaliacao implements Serializable {
	private static final long serialVersionUID = 5911361446102928715L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@ManyToOne
	private Vaga vaga;
	@ManyToOne
	private Candidato candidato;
	private Double nota;
	@Transient
	private boolean recomendada = false;

	public Avaliacao() {
	}

	public Avaliacao(Vaga vaga, Candidato candidato) {
		this.vaga = vaga;
		this.candidato = candidato;
	}

	public Avaliacao(Long id,Candidato candidato, Vaga vaga, Double nota) {
		this.id = id;
		this.vaga = vaga;
		this.candidato = candidato;
		this.nota = nota;
	}

	public Avaliacao(Vaga vaga, Candidato candidato, Double nota, boolean recomendada) {
		this.vaga = vaga;
		this.candidato = candidato;
		this.nota = nota;
		this.recomendada = recomendada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Integer getNotaInt() {
		if (nota == null) nota = 0.0;
		return nota.intValue();
	}

	public void setNotaInt(Integer nota) {
		this.nota = nota.doubleValue();
	}

	public Long getIdVaga() {
		return this.vaga.getId();
	}

	public boolean isRecomendada() {
		return recomendada;
	}

	public void setRecomendada(boolean recomendada) {
		this.recomendada = recomendada;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Avaliacao avaliacao = (Avaliacao) o;
		return Objects.equals(id, avaliacao.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}
