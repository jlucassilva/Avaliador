package intelligence

import model.Candidato
import util.Similaridade

class RankPorCandidato {
    var item: Candidato? = null
    var similaridade: Double = 0.toDouble()

    constructor()

    constructor(item: Candidato, similaridade: Double) {
        this.item = item
        this.similaridade = similaridade
    }

    companion object {
        fun comparaTodosCom(principal: Candidato, outrosCandidatos: MutableList<Candidato>): List<RankPorCandidato> {
            outrosCandidatos.remove(principal)
            return outrosCandidatos.map { outro -> RankPorCandidato(outro, Similaridade.pearson(principal, outro)) }
                    .sortedBy { rank -> rank.similaridade }
                    .reversed()
                    .toList()
        }
    }
}
