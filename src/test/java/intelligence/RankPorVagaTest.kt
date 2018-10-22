package intelligence

import common.Candidatos
import common.Util
import model.Avaliacao
import model.Candidato
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


class RankPorVagaTest {
    private var avaliacoes: List<Avaliacao>? = null

    @Before
    fun setUp() {
        avaliacoes = Util.inicializaAvaliacaos()
    }

    @Test
    fun deveObterRank() {
        val candidato = Candidatos.Toby.candidato
        candidato.avaliacoes = encontraAvaliacoesDe(candidato)

        val ranking = RankPorVaga().encontraRecomendacoes(candidato, todosOsCandidatos())

        assertEquals(3.3477895267131013, ranking[0].similaridade, 0.000000000001)
        assertEquals(2.8325499182641614, ranking[1].similaridade, 0.000000000001)
        assertEquals(2.5309807037655645, ranking[2].similaridade, 0.000000000001)

    }

    private fun encontraAvaliacoesDe(candidato: Candidato): Set<Avaliacao> {
        return avaliacoes!!
                .asSequence()
                .filter { critica -> critica.candidato == candidato }
                .toSet()
    }

    private fun todosOsCandidatos(): MutableList<Candidato> {
        return Candidatos.values()
                .map { this.criaCandidato(it) }
                .toList()
                .toMutableList()
    }

    private fun criaCandidato(candidatos: Candidatos): Candidato {
        return Candidato(candidatos.candidato.id, candidatos.nome, encontraAvaliacoesDe(candidatos.candidato))
    }
}