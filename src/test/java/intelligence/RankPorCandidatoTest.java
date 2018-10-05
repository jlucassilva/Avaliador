package intelligence;

import common.Candidatos;
import common.Util;
import model.Avaliacao;
import model.Candidato;
import org.junit.Before;
import org.junit.Test;
import util.Similaridade;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class RankPorCandidatoTest {
	private List<Avaliacao> avaliacoes;

	@Before
	public void setUp() {
		avaliacoes = Util.inicializaAvaliacaos();
	}

	@Test
	public void deveTrazer3() {
		Candidato rose = Candidatos.Rose.getCandidato();
		rose.setAvaliacoes(encontraAvaliacoesDe(rose));

		Candidato seymour = Candidatos.Seymour.getCandidato();
		seymour.setAvaliacoes(encontraAvaliacoesDe(seymour));
		assertEquals(seymour.getAvaliacoes().size() , 6);
	}

	@Test
	public void testaSimilaridadeComPearson() {
		Candidato rose = Candidatos.Rose.getCandidato();
		rose.setAvaliacoes(encontraAvaliacoesDe(rose));

		Candidato seymour = Candidatos.Seymour.getCandidato();
		seymour.setAvaliacoes(encontraAvaliacoesDe(seymour));


		double similaridadePearson = Similaridade.pearson(rose, seymour);

		assertEquals(0.396059017191, similaridadePearson, 0.000000000001);
	}

	@Test
	public void rankingBaseadoEmUsuarioComPearson() {
		Candidato toby = Candidatos.Toby.getCandidato();
		toby.setAvaliacoes(encontraAvaliacoesDe(toby));

		List<RankPorCandidato> ranking = RankPorCandidato.comparaTodosCom(toby, todosOsCandidatos());
		assertEquals(0.9912407071619302, ranking.get(0).getSimilaridade(), 0.000000000001);
		assertEquals(0.9244734516419048, ranking.get(1).getSimilaridade(), 0.000000000001);
		assertEquals(0.8934051474415647, ranking.get(2).getSimilaridade(), 0.000000000001);
	}

	@Test
	public void deveTer3EmComum() {
		Candidato toby = Candidatos.Toby.getCandidato();
		toby.setAvaliacoes(encontraAvaliacoesDe(toby));

		Candidato rose = Candidatos.Rose.getCandidato();
		rose.setAvaliacoes(encontraAvaliacoesDe(rose));

		Set<Avaliacao> roses = Similaridade.retainAll(rose.getAvaliacoes(), toby.getAvaliacoes());
		Set<Avaliacao> tobys = Similaridade.retainAll(rose.getAvaliacoes(), toby.getAvaliacoes());

		assertEquals(3, tobys.size());
		assertEquals(3, roses.size());
	}


	private List<Candidato> todosOsCandidatos() {
		return Arrays.stream(Candidatos.values())
				.map(this::criaCandidato)
				.collect(Collectors.toList());
	}

	private Candidato criaCandidato(Candidatos candidato) {
		return new Candidato(candidato.getCandidato().getId(),candidato.getCandidato().getNome(), encontraAvaliacoesDe(candidato.getCandidato()));
	}

	private Set<Avaliacao> encontraAvaliacoesDe(Candidato candidato) {
		avaliacoes.stream()
				.filter(critica -> critica.getCandidato().equals(candidato))
				.collect(Collectors.toList());
		return avaliacoes.stream()
				.filter(critica -> critica.getCandidato().equals(candidato))
				.collect(Collectors.toSet());
	}


}