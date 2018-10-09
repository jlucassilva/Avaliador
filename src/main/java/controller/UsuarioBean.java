package controller;

import kontroller.Usuario;
import model.Anunciante;
import model.Candidato;
import model.Competencia;
 import service.AnuncianteService;
import service.CandidatoService;
import service.CompetenciaService;
import service.UsuarioService;
import service.exception.ServiceException;
import util.Util;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 6658896176906049876L;

	@Inject
	private UsuarioService usuarioService;
	@Inject
	private CandidatoService candidatoService;
	@Inject
	private CompetenciaService competenciaService;
	@Inject
	private AnuncianteService anuncianteService;

	private Usuario usuarioSelecionado;
	private List<Usuario> usuarios;
	private List<Competencia> competencias;
	private List<Competencia> competenciasSelecionadas;

	private Boolean anunciante = false;

	@PostConstruct
	public void init() {
		usuarios = new ArrayList<>();
		usuarioSelecionado = new Usuario();
		atualizarCompetencias();
	}

	private void atualizarCompetencias() {
		competencias = competenciaService.listarTodos();
		competenciasSelecionadas = new ArrayList<>();
		if (competencias != null && !competencias.isEmpty())
			competenciasSelecionadas.add(competencias.get(new Random().nextInt(competencias.size())));
	}

	public void salvarOuAtualizar() {
		try {
			if (anunciante) {
				usuarioSelecionado.setCandidato(null);
				Anunciante anunciante = new Anunciante(usuarioSelecionado.getNome(), usuarioSelecionado);
				anuncianteService.salvar(anunciante);
			} else {
				Candidato candidato = new Candidato();
				candidato.setNome(usuarioSelecionado.getNome());
				candidato.setUsuario(usuarioSelecionado);
				candidato.setCompetencias(competenciasSelecionadas);
				usuarioSelecionado.setCandidato(null);

				candidatoService.salvar(candidato);
			}

			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/login.xhtml");

		} catch (ServiceException | IOException e) {
			e.printStackTrace();
		}
	}

	public List<Competencia> completaCompetencias(String descricao) {
		return competencias.stream()
				.filter(competencia -> {
					List<Competencia> c = new ArrayList<>();
					if (usuarioSelecionado.getCandidato() != null && usuarioSelecionado.getCandidato().getCompetencias() != null)
						c = usuarioSelecionado.getCandidato().getCompetencias();
					return competencia.getDescricao().toLowerCase().contains(descricao.toLowerCase())
							&& !c.contains(competencia);
				})
				.collect(Collectors.toList());
	}


	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	public Boolean getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(Boolean anunciante) {
		this.anunciante = anunciante;
	}

	public List<Competencia> getCompetenciasSelecionadas() {
		return competenciasSelecionadas;
	}

	public void setCompetenciasSelecionadas(List<Competencia> competenciasSelecionadas) {
		this.competenciasSelecionadas = competenciasSelecionadas;
	}
}
