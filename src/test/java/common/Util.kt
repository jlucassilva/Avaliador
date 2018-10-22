package common

import model.Avaliacao
import java.util.ArrayList

object Util {

    fun inicializaAvaliacaos(): List<Avaliacao> {
        return object : ArrayList<Avaliacao>() {
            init {
                add(Avaliacao(1L, Candidatos.Rose.candidato, Vagas.LADY_WATER.vaga, 2.5))
                add(Avaliacao(2L, Candidatos.Rose.candidato, Vagas.SNAKES_ON_A_PLANE.vaga, 3.5))
                add(Avaliacao(3L, Candidatos.Rose.candidato, Vagas.JUST_MY_LUCK.vaga, 3.0))
                add(Avaliacao(4L, Candidatos.Rose.candidato, Vagas.SUPERMAN_RETURNS.vaga, 3.5))
                add(Avaliacao(5L, Candidatos.Rose.candidato, Vagas.YOU_ME_DUPREE.vaga, 2.5))
                add(Avaliacao(6L, Candidatos.Rose.candidato, Vagas.NIGHT_LISTENER.vaga, 3.0))

                add(Avaliacao(7L, Candidatos.Seymour.candidato, Vagas.LADY_WATER.vaga, 3.0))
                add(Avaliacao(8L, Candidatos.Seymour.candidato, Vagas.SNAKES_ON_A_PLANE.vaga, 3.5))
                add(Avaliacao(9L, Candidatos.Seymour.candidato, Vagas.JUST_MY_LUCK.vaga, 1.5))
                add(Avaliacao(10L, Candidatos.Seymour.candidato, Vagas.SUPERMAN_RETURNS.vaga, 5.0))
                add(Avaliacao(11L, Candidatos.Seymour.candidato, Vagas.YOU_ME_DUPREE.vaga, 3.5))
                add(Avaliacao(12L, Candidatos.Seymour.candidato, Vagas.NIGHT_LISTENER.vaga, 3.0))

                add(Avaliacao(13L, Candidatos.Phillips.candidato, Vagas.LADY_WATER.vaga, 2.5))
                add(Avaliacao(14L, Candidatos.Phillips.candidato, Vagas.SNAKES_ON_A_PLANE.vaga, 3.0))
                add(Avaliacao(15L, Candidatos.Phillips.candidato, Vagas.SUPERMAN_RETURNS.vaga, 3.5))
                add(Avaliacao(16L, Candidatos.Phillips.candidato, Vagas.NIGHT_LISTENER.vaga, 4.0))

                add(Avaliacao(17L, Candidatos.Puig.candidato, Vagas.SNAKES_ON_A_PLANE.vaga, 3.5))
                add(Avaliacao(18L, Candidatos.Puig.candidato, Vagas.JUST_MY_LUCK.vaga, 3.0))
                add(Avaliacao(19L, Candidatos.Puig.candidato, Vagas.SUPERMAN_RETURNS.vaga, 4.0))
                add(Avaliacao(20L, Candidatos.Puig.candidato, Vagas.YOU_ME_DUPREE.vaga, 2.5))
                add(Avaliacao(21L, Candidatos.Puig.candidato, Vagas.NIGHT_LISTENER.vaga, 4.5))

                add(Avaliacao(22L, Candidatos.LaSalle.candidato, Vagas.LADY_WATER.vaga, 3.0))
                add(Avaliacao(23L, Candidatos.LaSalle.candidato, Vagas.SNAKES_ON_A_PLANE.vaga, 4.0))
                add(Avaliacao(24L, Candidatos.LaSalle.candidato, Vagas.JUST_MY_LUCK.vaga, 2.0))
                add(Avaliacao(25L, Candidatos.LaSalle.candidato, Vagas.SUPERMAN_RETURNS.vaga, 3.0))
                add(Avaliacao(26L, Candidatos.LaSalle.candidato, Vagas.YOU_ME_DUPREE.vaga, 2.0))
                add(Avaliacao(27L, Candidatos.LaSalle.candidato, Vagas.NIGHT_LISTENER.vaga, 3.0))

                add(Avaliacao(28L, Candidatos.Matthews.candidato, Vagas.LADY_WATER.vaga, 3.0))
                add(Avaliacao(29L, Candidatos.Matthews.candidato, Vagas.SNAKES_ON_A_PLANE.vaga, 4.0))
                add(Avaliacao(30L, Candidatos.Matthews.candidato, Vagas.SUPERMAN_RETURNS.vaga, 5.0))
                add(Avaliacao(31L, Candidatos.Matthews.candidato, Vagas.YOU_ME_DUPREE.vaga, 3.5))
                add(Avaliacao(32L, Candidatos.Matthews.candidato, Vagas.NIGHT_LISTENER.vaga, 3.0))

                add(Avaliacao(33L, Candidatos.Toby.candidato, Vagas.SNAKES_ON_A_PLANE.vaga, 4.5))
                add(Avaliacao(34L, Candidatos.Toby.candidato, Vagas.SUPERMAN_RETURNS.vaga, 4.0))
                add(Avaliacao(35L, Candidatos.Toby.candidato, Vagas.YOU_ME_DUPREE.vaga, 1.0))
            }
        }
    }
}
