package model;

import kontroller.Usuario;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Candidato {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	private String nome;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Usuario usuario;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Competencia> competencias;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Avaliacao> avaliacoes;

	public Candidato() {
	}

	public Candidato(String nome) {
		this.nome = nome;
	}

	public Candidato(Long id, String nome) {
		this.nome = nome;
		this.id = id;
	}


	public Candidato(Long id,String nome, Set<Avaliacao> avaliacoes) {
		this.id = id;
		this.nome = nome;
		this.avaliacoes = avaliacoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	public Set<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(Set<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Candidato candidato = (Candidato) o;
		return Objects.equals(id, candidato.id) &&
				Objects.equals(nome, candidato.nome);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, nome);
	}
}
