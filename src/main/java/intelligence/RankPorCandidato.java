package intelligence;

import model.Candidato;
import util.Similaridade;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RankPorCandidato {
	private Candidato item;
	private double similaridade;


	public RankPorCandidato() {
	}

	public RankPorCandidato(Candidato item, double similaridade) {
		this.item = item;
		this.similaridade = similaridade;
	}

	public Candidato getItem() {
		return item;
	}

	public void setItem(Candidato item) {
		this.item = item;
	}

	public double getSimilaridade() {
		return similaridade;
	}

	public void setSimilaridade(double similaridade) {
		this.similaridade = similaridade;
	}

	public static List<RankPorCandidato> comparaTodosCom(Candidato principal, List<Candidato> outrosCandidatos) {
		outrosCandidatos.remove(principal);
		return outrosCandidatos.stream()
				.map(outro -> new RankPorCandidato(outro, Similaridade.pearson(principal, outro)))
				.sorted((Comparator.comparingDouble(RankPorCandidato::getSimilaridade).reversed()))
				.collect(Collectors.toList());
	}
}
