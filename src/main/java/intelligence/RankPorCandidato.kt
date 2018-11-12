package intelligence

import model.Candidato
import util.Similaridade

class RankPorCandidato(item: Candidato, var similaridade: Double) {
    var item: Candidato? = item

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
