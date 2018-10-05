package controller;


import intelligence.RankPorVaga;
import model.Avaliacao;
import model.Candidato;
import model.Usuario;
import model.Vaga;
import org.hsqldb.lib.Collection;
import org.primefaces.event.RateEvent;
import service.AvaliacaoService;
import service.CandidatoService;
import service.UsuarioService;
import service.VagaService;
import service.exception.ServiceException;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class AvaliacaoBean implements Serializable {
	private static final long serialVersionUID = -860487912181614461L;

	@Inject
	private VagaService vagaService;

	@Inject
	private AvaliacaoService avaliacaoService;

	@Inject
	private SessionBean usuario;

	@Inject
	private CandidatoService candidatoService;

	@Inject
	private UsuarioService usuarioService;

	private Avaliacao avaliacao;
	private List<Avaliacao> avaliacoes;
	private List<Avaliacao> avaliacoesFiltradas;
	private List<Vaga> vagasRecomendadas;

	@PostConstruct
	public void init() {
		if (filtraUsuario()) {
			avaliacao = new Avaliacao();
			vagasRecomendadas = new ArrayList<>();
			carregaAvaliacoes();
			carregarCandidatos();
		}
	}

	private boolean filtraUsuario() {
		if (usuario.getUsuarioLogado().getCandidato() == null) {
			try {
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				ec.redirect(ec.getRequestContextPath() + "/vaga.xhtml");
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private void carregarCandidatos() {
		encontraRecomendacoes();
	}

	private void carregaAvaliacoes() {
		Candidato candidato = usuario.getUsuarioLogado().getCandidato();
		avaliacoes = new ArrayList<>(avaliacaoService.listarTodasDe(candidato));
		filtraAvaliacoes(candidato);
	}

	private void filtraAvaliacoes(Candidato candidato) {
		List<Vaga> vagaSemAvaliacao = vagaService.obterVagasSemAvaliacaoPelo(candidato);

		List<Avaliacao> recomendadas = encontraRecomendacoes();
		vagaSemAvaliacao.removeAll(recomendadas.stream().map(Avaliacao::getVaga).collect(Collectors.toList()));

		List<Avaliacao> porCompetencia = new ArrayList<>();
		vagaSemAvaliacao.stream().filter(vaga -> !Collections.disjoint(vaga.getCompetencias(), candidato.getCompetencias()))
				.forEach(vaga -> porCompetencia.add(new Avaliacao(vaga, candidato)));

		porCompetencia.addAll(avaliacoes);

		vagaSemAvaliacao.removeAll(porCompetencia.stream().map(Avaliacao::getVaga).collect(Collectors.toList()));
		vagaSemAvaliacao.forEach(vaga -> porCompetencia.add(new Avaliacao(vaga, candidato)));

		avaliacoes.clear();
		avaliacoes.addAll(recomendadas);
		avaliacoes.addAll(porCompetencia);
	}

	private List<Avaliacao> encontraRecomendacoes() {
		Candidato principal = usuario.getUsuarioLogado().getCandidato();
		principal.setAvaliacoes(avaliacaoService.listarTodasDe(principal));
		List<Candidato> outrosCandidatos = new ArrayList<>();

		candidatoService.listarTodos().forEach(candidato -> {
			candidato.setAvaliacoes(avaliacaoService.listarTodasDe(candidato));
			outrosCandidatos.add(candidato);
		});

		outrosCandidatos.remove(principal);
		List<RankPorVaga> ranking = new RankPorVaga().encontraRecomendacoes(principal, outrosCandidatos);

		System.out.println(ranking.stream()
				.filter(rankVaga -> rankVaga.getSimilaridade() > 3.0).count());
		return ranking.stream()
				.filter(rankVaga -> rankVaga.getSimilaridade() > 3.0)
				.map(rankVaga -> new Avaliacao(rankVaga.getItem(), principal, rankVaga.getSimilaridade(), true))
				.collect(Collectors.toList());

	}

	public void avaliando(Avaliacao ava) {
		try {
			if (ava.isRecomendada())
				ava.setRecomendada(false);
			avaliacaoService.atualizar(ava);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public List<Avaliacao> getAvaliacoesFiltradas() {
		return avaliacoesFiltradas;
	}

	public void setAvaliacoesFiltradas(List<Avaliacao> avaliacoesFiltradas) {
		this.avaliacoesFiltradas = avaliacoesFiltradas;
	}

	public List<Vaga> getVagasRecomendadas() {
		return vagasRecomendadas;
	}

	public void setVagasRecomendadas(List<Vaga> vagasRecomendadas) {
		this.vagasRecomendadas = vagasRecomendadas;
	}
}
