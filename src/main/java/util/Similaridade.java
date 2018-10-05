package util;

import model.Avaliacao;
import model.Candidato;
import model.Vaga;

import java.util.*;
import java.util.stream.Collectors;

public class Similaridade {

	public static double pearson(Candidato principal, Candidato outro) {

		Set<Avaliacao> avaliacoesPrincipal = new HashSet<>(principal.getAvaliacoes());
		Set<Avaliacao> avaliacoesOutro = new HashSet<>(outro.getAvaliacoes());

		avaliacoesPrincipal = retainAll(avaliacoesPrincipal, avaliacoesOutro);
		avaliacoesOutro = retainAll(avaliacoesOutro, avaliacoesPrincipal);

		long n = avaliacoesPrincipal.size();
		double totalNotasPrincipal = obterSomaDasNotas(avaliacoesPrincipal);
		double totalNotasOutro = obterSomaDasNotas(avaliacoesOutro);

		double totalDosQuadradosPrincipal = obterSomaDosQuadrados(avaliacoesPrincipal);
		double totalDosQuadradosOutro = obterSomaDosQuadrados(avaliacoesOutro);

		double totalDosProdutos = obterSomaDosProdutos(avaliacoesPrincipal, avaliacoesOutro);

		double pearsonScore = (n * totalDosProdutos) - (1.0 * totalNotasPrincipal * totalNotasOutro);
		double den1 = Math.sqrt((n * totalDosQuadradosPrincipal) - Math.pow(totalNotasPrincipal, 2));
		double den2 = Math.sqrt((n * totalDosQuadradosOutro) - Math.pow(totalNotasOutro, 2));
		double den = 1.0 * den1 * den2;

		if (den == 0) return 0;

		return pearsonScore / den;
	}


	private static double obterSomaDasNotas(Set<Avaliacao> avaliacoes) {
		return avaliacoes.stream()
				.map(Avaliacao::getNota)
				.mapToDouble(Number::doubleValue)
				.sum();
	}

	private static double obterSomaDosQuadrados(Set<Avaliacao> avaliacoes) {
		return avaliacoes.stream()
				.map(Similaridade::quadradoDaNota)
				.mapToDouble(Number::doubleValue)
				.sum();
	}

	private static Double quadradoDaNota(Avaliacao avaliacao) {
		return Math.pow(avaliacao.getNota(), 2);
	}

	private static double obterSomaDosProdutos(Set<Avaliacao> principal, Set<Avaliacao> outro) {
		List<Avaliacao> principalOrdenado = principal.stream().sorted(Comparator.comparing(Avaliacao::getIdVaga)).collect(Collectors.toList());
		List<Avaliacao> outroOrdenado = outro.stream().sorted(Comparator.comparing(Avaliacao::getIdVaga)).collect(Collectors.toList());

		Iterator<Avaliacao> pIt = principalOrdenado.iterator();
		Iterator<Avaliacao> oIt = outroOrdenado.iterator();
		double somatorio = 0;

		while (pIt.hasNext() && oIt.hasNext()) {
			somatorio += pIt.next().getNota() * oIt.next().getNota();
		}
		return somatorio;
	}

	public static double euclidiana(Candidato principal, Candidato outro) {
		double somatorioDasSimilaridades = principal.getAvaliacoes().stream()
				.map(avaliacaoPrincipal -> {
					Avaliacao avaliacaoOutro = encontraAvaliacaoDaVaga(avaliacaoPrincipal.getVaga(), outro.getAvaliacoes());
					if (avaliacaoOutro != null)
						return distanciaEuclidiana(avaliacaoPrincipal.getNota(), avaliacaoOutro.getNota());
					return 0;
				}).mapToDouble(Number::doubleValue)
				.sum();
		return 1 / (1 + Math.sqrt(somatorioDasSimilaridades));
	}

	private static double distanciaEuclidiana(double nota1, double nota2) {
		return Math.pow(nota1 - nota2, 2);
	}

	private static Avaliacao encontraAvaliacaoDaVaga(Vaga vaga, Set<Avaliacao> avaliacoes) {
		return avaliacoes.stream()
				.filter(avaliacao -> vaga == avaliacao.getVaga())
				.findFirst()
				.orElse(null);
	}

	public static Set<Avaliacao> retainAll(Set<Avaliacao> avaliacoesPrincipal, Set<Avaliacao> avaliacoesOutro) {
		return avaliacoesPrincipal.stream()
				.filter(avaliacao -> avaliacoesOutro.stream()
						.anyMatch(avaliacaoOutro -> avaliacaoOutro.getVaga().equals(avaliacao.getVaga())))
				.collect(Collectors.toSet());
	}
}
