package intelligence

import common.Candidatos
import common.Util
import model.Avaliacao
import model.Candidato
import org.junit.Before
import org.junit.Test
import util.Similaridade
import org.junit.Assert.assertEquals


class RankPorCandidatoTest {
    private var avaliacoes: List<Avaliacao>? = null

    @Before
    fun setUp() {
        avaliacoes = Util.inicializaAvaliacaos()
    }

    @Test
    fun deveTrazer3() {
        val rose = Candidatos.Rose.candidato
        rose.avaliacoes = encontraAvaliacoesDe(rose)

        val seymour = Candidatos.Seymour.candidato
        seymour.avaliacoes = encontraAvaliacoesDe(seymour)
        assertEquals(seymour.avaliacoes.size.toLong(), 6)
    }

    @Test
    fun testaSimilaridadeComPearson() {
        val rose = Candidatos.Rose.candidato
        rose.avaliacoes = encontraAvaliacoesDe(rose)

        val seymour = Candidatos.Seymour.candidato
        seymour.avaliacoes = encontraAvaliacoesDe(seymour)


        val similaridadePearson = Similaridade.pearson(rose, seymour)

        assertEquals(0.396059017191, similaridadePearson, 0.000000000001)
    }

    @Test
    fun rankingBaseadoEmUsuarioComPearson() {
        val toby = Candidatos.Toby.candidato
        toby.avaliacoes = encontraAvaliacoesDe(toby)

        val ranking = RankPorCandidato.comparaTodosCom(toby, todosOsCandidatos())
        assertEquals(0.9912407071619302, ranking[0].similaridade, 0.000000000001)
        assertEquals(0.9244734516419048, ranking[1].similaridade, 0.000000000001)
        assertEquals(0.8934051474415647, ranking[2].similaridade, 0.000000000001)
    }

    @Test
    fun deveTer3EmComum() {
        val toby = Candidatos.Toby.candidato
        toby.avaliacoes = encontraAvaliacoesDe(toby)

        val rose = Candidatos.Rose.candidato
        rose.avaliacoes = encontraAvaliacoesDe(rose)

        val roses = Similaridade.retainAll(rose.avaliacoes, toby.avaliacoes)
        val tobys = Similaridade.retainAll(rose.avaliacoes, toby.avaliacoes)

        assertEquals(3, tobys.size.toLong())
        assertEquals(3, roses.size.toLong())
    }


    private fun todosOsCandidatos(): MutableList<Candidato> {
        return Candidatos.values()
                .map { this.criaCandidato(it) }
                .toList()
                .toMutableList()
    }

    private fun criaCandidato(candidato: Candidatos): Candidato {
        return Candidato(candidato.candidato.id, candidato.candidato.nome!!, encontraAvaliacoesDe(candidato.candidato))
    }

    private fun encontraAvaliacoesDe(candidato: Candidato): Set<Avaliacao> {
        return avaliacoes!!
                .asSequence()
                .filter { critica -> critica.candidato == candidato }
                .toSet()
    }


}