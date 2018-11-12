package dao

import model.Candidato
import java.io.Serializable

open class CandidatoDao : AbstractDao<Candidato>(Candidato::class.java), Serializable {

    fun listarTodosComAvaliacoes(): List<Candidato> {
        return getEntityManager().createQuery(
                "select c from Candidato c ",
                Candidato::class.java)
                .resultList
    }

}