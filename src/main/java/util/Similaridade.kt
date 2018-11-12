package util

import model.Avaliacao
import model.Candidato
import model.Vaga
import java.util.*

object Similaridade {

    fun pearson(principal: Candidato, outro: Candidato): Double {

        var avaliacoesPrincipal: Set<Avaliacao> = HashSet(principal.avaliacoes)
        var avaliacoesOutro: Set<Avaliacao> = HashSet(outro.avaliacoes)

        avaliacoesPrincipal = retainAll(avaliacoesPrincipal, avaliacoesOutro)
        avaliacoesOutro = retainAll(avaliacoesOutro, avaliacoesPrincipal)

        val n = avaliacoesPrincipal.size.toLong()
        val totalNotasPrincipal = obterSomaDasNotas(avaliacoesPrincipal)
        val totalNotasOutro = obterSomaDasNotas(avaliacoesOutro)

        val totalDosQuadradosPrincipal = obterSomaDosQuadrados(avaliacoesPrincipal)
        val totalDosQuadradosOutro = obterSomaDosQuadrados(avaliacoesOutro)

        val totalDosProdutos = obterSomaDosProdutos(avaliacoesPrincipal, avaliacoesOutro)

        val pearsonScore = n * totalDosProdutos - 1.0 * totalNotasPrincipal * totalNotasOutro
        val den1 = Math.sqrt(n * totalDosQuadradosPrincipal - Math.pow(totalNotasPrincipal, 2.0))
        val den2 = Math.sqrt(n * totalDosQuadradosOutro - Math.pow(totalNotasOutro, 2.0))
        val den = 1.0 * den1 * den2

        return if (den == 0.0) 0.0 else pearsonScore / den
    }

    private fun obterSomaDasNotas(avaliacoes: Set<Avaliacao>): Double {
        return avaliacoes.map { it.nota }.sum()
    }

    private fun obterSomaDosQuadrados(avaliacoes: Set<Avaliacao>): Double {
        return avaliacoes.map { quadradoDaNota(it) }.sum()
    }

    private fun quadradoDaNota(avaliacao: Avaliacao): Double {
        return Math.pow(avaliacao.nota, 2.0)
    }

    private fun obterSomaDosProdutos(principal: Set<Avaliacao>, outro: Set<Avaliacao>): Double {
        val principalOrdenado = principal.sortedBy { it.getIdVaga() }.toList()
        val outroOrdenado = outro.sortedBy { it.getIdVaga() }.toList()

        val pIt = principalOrdenado.iterator()
        val oIt = outroOrdenado.iterator()
        var somatorio = 0.0

        while (pIt.hasNext() && oIt.hasNext()) {
            somatorio += pIt.next().nota * oIt.next().nota
        }
        return somatorio
    }

    fun euclidiana(principal: Candidato, outro: Candidato): Double {
        val somatorioDasSimilaridades = principal.avaliacoes
                .map { avaliacaoPrincipal ->
                    try {
                        val avaliacaoOutro = encontraAvaliacaoDaVaga(avaliacaoPrincipal.vaga, outro.avaliacoes)
                        distanciaEuclidiana(avaliacaoPrincipal.nota, avaliacaoOutro!!.nota)
                    } catch (e: NoSuchElementException) {
                        0.0
                    }
                }.sum()
        return 1 / (1 + Math.sqrt(somatorioDasSimilaridades))
    }

    private fun distanciaEuclidiana(nota1: Double, nota2: Double): Double {
        return Math.pow(nota1 - nota2, 2.0)
    }

    private fun encontraAvaliacaoDaVaga(vaga: Vaga?, avaliacoes: Set<Avaliacao>): Avaliacao? {
        return avaliacoes.filter { avaliacao -> vaga == avaliacao.vaga }
                .first()
    }

    fun retainAll(avaliacoesPrincipal: Set<Avaliacao>, avaliacoesOutro: Set<Avaliacao>): Set<Avaliacao> {
        return avaliacoesPrincipal.filter { avaliacao ->
            avaliacoesOutro.any { avaliacaoOutro -> avaliacaoOutro.vaga == avaliacao.vaga }
        }.toSet()
    }
}