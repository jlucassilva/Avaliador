package dao

import model.Avaliacao
import model.Candidato
import model.Usuario
import java.io.Serializable

open class AvaliacaoDaoK: AbstractDaoK<Avaliacao>(Avaliacao::class.java), Serializable {

    fun listarTodasDe(candidato: Candidato): List<Avaliacao> {
        return getEntityManager().createQuery("select a from Avaliacao a "
                + "where a.candidato = :candidato", Avaliacao::class.java)
                .setParameter("candidato", candidato)
                .resultList
    }

}