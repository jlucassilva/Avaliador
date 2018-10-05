package model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 8511735530142157411L;
	@Id
	@GeneratedValue(
			strategy = GenerationType.AUTO,
			generator = "native")
	@GenericGenerator(
			name = "native",
			strategy = "native")
	private Long id;

	@Column(unique = true)
	private String username;
	@Column
	private String password;
	@Column
	private String nome;
	@OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Candidato candidato;
	@OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Anunciante anunciante;

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Anunciante getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Anunciante anunciante) {
		this.anunciante = anunciante;
	}
}
