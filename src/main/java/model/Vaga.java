package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Vaga implements Serializable {
	private static final long serialVersionUID = 2739927316231062403L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	private String titulo;
	@Lob
	@Column(columnDefinition = "text")
	private String descricao;
	private double salario;
	@ManyToOne(fetch = FetchType.LAZY)
	private Anunciante anunciante;
	private LocalDateTime dataDoAnucio;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Avaliacao> avaliacoes;
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Competencia> competencias;

	public Vaga() {
	}

	public Vaga(Long id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}

	public Vaga(Vaga vaga) {
		this.id = vaga.id;
		this.titulo = vaga.titulo;
		this.descricao = vaga.descricao;
		this.salario = vaga.salario;
		this.dataDoAnucio = vaga.dataDoAnucio;
		this.competencias = vaga.competencias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public LocalDateTime getDataDoAnucio() {
		return dataDoAnucio;
	}

	public void setDataDoAnucio(LocalDateTime dataDoAnucio) {
		this.dataDoAnucio = dataDoAnucio;
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}
}
