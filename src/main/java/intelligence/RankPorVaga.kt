package intelligence

import model.Candidato
import model.Vaga
import util.Similaridade
import java.util.*


class RankPorVaga {
    var item: Vaga? = null
    var similaridade: Double = 0.toDouble()

    constructor()

    constructor(item: Vaga, similaridade: Double) {
        this.item = item
        this.similaridade = similaridade
    }

    fun encontraRecomendacoes(principal: Candidato, outroCandidatos: MutableList<Candidato>): List<RankPorVaga> {
        outroCandidatos.remove(principal)

        val totals = HashMap<Vaga, Double>()
        val totalSimilaridades = HashMap<Vaga, Double>()

        for (outro in outroCandidatos) {
            val similaridade = Similaridade.pearson(principal, outro)
            if (similaridade > 0) {

                outro.avaliacoes = with(outro.avaliacoes) {
                    val temp = this.toMutableSet()
                    temp.removeIf { avaliacao -> encontraVagaEm(avaliacao.vaga, principal) }
                    temp.toSet()
                }

                for (avaliacao in outro.avaliacoes) {
                    var total: Double? = totals[avaliacao.vaga]
                    if (total == null) total = 0.0
                    total += avaliacao.nota * similaridade
                    totals[avaliacao.vaga!!] = total

                    var totalSim: Double? = totalSimilaridades[avaliacao.vaga!!]
                    if (totalSim == null) totalSim = 0.0

                    totalSim += similaridade
                    totalSimilaridades[avaliacao.vaga!!] = totalSim
                }
            }
        }

        val ranking = ArrayList<RankPorVaga>()
        for (vaga in totals.keys) {
            val score = totals[vaga]!! / totalSimilaridades[vaga]!!
            ranking.add(RankPorVaga(vaga, score))
        }

        return ranking
                .sortedBy { rankPorVaga -> rankPorVaga.similaridade }
                .reversed()
    }

    private fun encontraVagaEm(vaga: Vaga?, principal: Candidato): Boolean {
        return principal.avaliacoes.stream().anyMatch { avaliacaoPrincipal -> avaliacaoPrincipal.vaga == vaga }
    }

}
