//package intelligence;
//
//import model.Avaliacao;
//import model.Candidato;
//import model.Vaga;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class Recomendacoes {
//
//	public List<RankPorVaga> encontraRecomendacoes(Candidato principal, List<Candidato> outroCandidatos) {
//		outroCandidatos.remove(principal);
//
//		Map<Vaga, Double> totals = new HashMap<>();
//		Map<Vaga, Double> totalSimilaridades = new HashMap<>();
//
//		outroCandidatos.forEach(candidato -> {
//			double similaridade = similaridadePearson(principal, candidato);
//			if (similaridade > 0) {
//				candidato.getAvaliacoes().removeAll(principal.getAvaliacoes());
//				candidato.getAvaliacoes().forEach(avaliacao -> {
//					Double total = totals.get(avaliacao.getVaga());
//					if (total == null) total = 0.0;
//					total += avaliacao.getNota() * similaridade;
//					totals.put(avaliacao.getVaga(), total);
//
//					Double totalSim = totalSimilaridades.get(avaliacao.getVaga());
//					if (totalSim == null) totalSim = 0.0;
//
//					totalSim += similaridade;
//					totalSimilaridades.put(avaliacao.getVaga(), totalSim);
//				});
//			}
//		});
//
//		List<RankPorVaga> ranking = new ArrayList<>();
//		for (Vaga vaga : totals.keySet()) {
//			double score = totals.get(vaga) / totalSimilaridades.get(vaga);
//			ranking.add(new RankPorVaga(vaga, score));
//		}
//		ranking.sort(Comparator.comparing(RankPorVaga::getSimilaridade).reversed());
//		return ranking;
//	}
//
//	public List<RankPorCandidato> comparaTodosCom(Candidato principal, List<Candidato> outrosCandidatos) {
//		outrosCandidatos.remove(principal);
//		return outrosCandidatos.stream()
//				.map(outro -> new RankPorCandidato(outro, similaridadePearson(principal, outro)))
//				.sorted((Comparator.comparingDouble(RankPorCandidato::getSimilaridade).reversed()))
//				.collect(Collectors.toList());
//	}
//
//	public double similaridadeEuclidiana(Candidato principal, Candidato outro) {
//		double somatorioDasSimilaridades = principal.getAvaliacoes().stream()
//				.map(avaliacaoPrincipal -> {
//					Avaliacao avaliacaoOutro = encontraAvaliacaoDoFilme(avaliacaoPrincipal.getVaga(), outro.getAvaliacoes());
//					if (avaliacaoOutro != null)
//						return euclideanDistanceScore(avaliacaoPrincipal.getNota(), avaliacaoOutro.getNota());
//					return 0;
//				}).mapToDouble(Number::doubleValue)
//				.sum();
//		return 1 / (1 + Math.sqrt(somatorioDasSimilaridades));
//	}
//
//	private static double euclideanDistanceScore(double nota1, double nota2) {
//		return Math.pow(nota1 - nota2, 2);
//	}
//
//	public double similaridadePearson(Candidato principal, Candidato outro) {
//
//		Set<Avaliacao> criticasPrincipal = new HashSet<>(principal.getAvaliacoes());
//		Set<Avaliacao> criticasOutro = new HashSet<>(outro.getAvaliacoes());
//
//		criticasPrincipal.retainAll(criticasOutro);
//		criticasOutro.retainAll(criticasPrincipal);
//
//		long n = criticasPrincipal.size();
//		double totalNotasPrincipal = obterSomaDasNotas(criticasPrincipal);
//		double totalNotasOutro = obterSomaDasNotas(criticasOutro);
//
//		double totalDosQuadradosPrincipal = obterSomaDosQuadrados(criticasPrincipal);
//		double totalDosQuadradosOutro = obterSomaDosQuadrados(criticasOutro);
//
//		double totalDosProdutos = obterSomaDosProdutos(criticasPrincipal, criticasOutro);
//
//		double pearsonScore = (n * totalDosProdutos) - (1.0 * totalNotasPrincipal * totalNotasOutro);
//		double den1 = Math.sqrt((n * totalDosQuadradosPrincipal) - Math.pow(totalNotasPrincipal, 2));
//		double den2 = Math.sqrt((n * totalDosQuadradosOutro) - Math.pow(totalNotasOutro, 2));
//		double den = 1.0 * den1 * den2;
//
//		if (den == 0) return 0;
//
//		return pearsonScore / den;
//	}
//
//	private double obterSomaDasNotas(Set<Avaliacao> criticas) {
//		return criticas.stream()
//				.map(Avaliacao::getNota)
//				.mapToDouble(Number::doubleValue)
//				.sum();
//	}
//
//	private double obterSomaDosQuadrados(Set<Avaliacao> criticas) {
//		return criticas.stream()
//				.map(Recomendacoes::quadradoDaNota)
//				.mapToDouble(Number::doubleValue)
//				.sum();
//	}
//
//	private static Double quadradoDaNota(Avaliacao avaliacao) {
//		return Math.pow(avaliacao.getNota(), 2);
//	}
//
//	private double obterSomaDosProdutos(Set<Avaliacao> principal, Set<Avaliacao> outro) {
//		principal = principal.stream().sorted(Comparator.comparing(Avaliacao::getFilme)).collect(Collectors.toSet());
//		outro = outro.stream().sorted(Comparator.comparing(Avaliacao::getNota)).collect(Collectors.toSet());
//
//		Iterator<Avaliacao> pIt = principal.iterator();
//		Iterator<Avaliacao> oIt = outro.iterator();
//
//		double somatorio = 0;
//
//		while (pIt.hasNext() && oIt.hasNext()) {
//			somatorio += pIt.next().nota * oIt.next().nota;
//		}
//		return somatorio;
//	}
//
//	private Avaliacao encontraAvaliacaoDoFilme(Vaga vaga, Set<Avaliacao> avaliacoes) {
//		return avaliacoes.stream()
//				.filter(avaliacao -> vaga == avaliacao.getVaga())
//				.findFirst()
//				.orElse(null);
//	}
//}
