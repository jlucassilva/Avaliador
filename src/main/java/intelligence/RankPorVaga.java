package intelligence;

import model.Avaliacao;
import model.Candidato;
import model.Vaga;
import util.Similaridade;

import java.util.*;

public class RankPorVaga {
	private Vaga item;
	private double similaridade;

	public RankPorVaga() {
	}

	public RankPorVaga(Vaga item, double similaridade) {
		this.item = item;
		this.similaridade = similaridade;
	}

	public Vaga getItem() {
		return item;
	}

	public void setItem(Vaga item) {
		this.item = item;
	}

	public double getSimilaridade() {
		return similaridade;
	}

	public void setSimilaridade(double similaridade) {
		this.similaridade = similaridade;
	}

	public List<RankPorVaga> encontraRecomendacoes(Candidato principal, List<Candidato> outroCandidatos) {
		outroCandidatos.remove(principal);

		Map<Vaga, Double> totals = new HashMap<>();
		Map<Vaga, Double> totalSimilaridades = new HashMap<>();

		for (Candidato outro : outroCandidatos) {
			double similaridade = Similaridade.pearson(principal, outro);
			if (similaridade > 0) {

				outro.getAvaliacoes().removeIf(avaliacao -> encontraVagaEm(avaliacao.getVaga(), principal));

				for (Avaliacao avaliacao : outro.getAvaliacoes()) {
					Double total = totals.get(avaliacao.getVaga());
					if (total == null) total = 0.0;
					total += avaliacao.getNota() * similaridade;
					totals.put(avaliacao.getVaga(), total);

					Double totalSim = totalSimilaridades.get(avaliacao.getVaga());
					if (totalSim == null) totalSim = 0.0;

					totalSim += similaridade;
					totalSimilaridades.put(avaliacao.getVaga(), totalSim);
				}
			}
		}

		List<RankPorVaga> ranking = new ArrayList<>();
		for (Vaga vaga : totals.keySet()) {
			double score = totals.get(vaga) / totalSimilaridades.get(vaga);
			ranking.add(new RankPorVaga(vaga, score));
		}
		ranking.sort(Comparator.comparing(RankPorVaga::getSimilaridade).reversed());
		return ranking;
	}

	private boolean encontraVagaEm(Vaga vaga, Candidato principal) {
		return principal.getAvaliacoes().stream().anyMatch(avaliacaoPrincipal -> avaliacaoPrincipal.getVaga().equals(vaga));
	}

}
