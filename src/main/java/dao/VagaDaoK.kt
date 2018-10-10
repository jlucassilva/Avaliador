package dao

import model.Candidato
import model.Vaga
import java.io.Serializable

open class VagaDaoK : AbstractDaoK<Vaga>(Vaga::class.java), Serializable {

    fun obterVagasSemAvaliacaoPelo(candidato: Candidato): List<Vaga> {
        return getEntityManager().createQuery("select v from Vaga v " +
                "where v not in " +
                "(select va.vaga from Avaliacao va " +
                "where va.candidato = :candidato)", Vaga::class.java)
                .setParameter("candidato", candidato)
                .resultList
    }

}