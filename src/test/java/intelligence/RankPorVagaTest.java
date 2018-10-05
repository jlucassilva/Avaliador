package intelligence;

import common.Candidatos;
import common.Util;
import model.Avaliacao;
import model.Candidato;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class RankPorVagaTest {
	private List<Avaliacao> avaliacoes;

	@Before
	public void setUp() {
		avaliacoes = Util.inicializaAvaliacaos();
	}

	@Test
	public void deveObterRank() {
		Candidato candidato = Candidatos.Toby.getCandidato();
		candidato.setAvaliacoes(encontraAvaliacoesDe(candidato));

		List<RankPorVaga> ranking = new RankPorVaga().encontraRecomendacoes(candidato, todosOsCandidatos());

		assertEquals(3.3477895267131013, ranking.get(0).getSimilaridade(), 0.000000000001);
		assertEquals(2.8325499182641614, ranking.get(1).getSimilaridade(), 0.000000000001);
		assertEquals(2.5309807037655645, ranking.get(2).getSimilaridade(), 0.000000000001);

	}

	private Set<Avaliacao> encontraAvaliacoesDe(Candidato candidato) {
		return avaliacoes.stream()
				.filter(critica -> critica.getCandidato().equals(candidato))
				.collect(Collectors.toSet());
	}

	private List<Candidato> todosOsCandidatos() {
		return Arrays.stream(Candidatos.values())
				.map(this::criaCandidato)
				.collect(Collectors.toList());
	}

	private Candidato criaCandidato(Candidatos candidatos) {
		return new Candidato(candidatos.getCandidato().getId(), candidatos.getName(), encontraAvaliacoesDe(candidatos.getCandidato()));
	}
}