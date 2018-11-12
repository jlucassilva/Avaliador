package service

import dao.AvaliacaoDao
import model.Avaliacao
import model.Candidato
import java.io.Serializable
import javax.inject.Inject

class AvaliacaoService @Inject constructor(var daoU: AvaliacaoDao) : Serializable, AbstractService<Avaliacao>(dao = daoU) {

    val serialVersionUID = -2800411759544395063L

    fun listarTodasDe(candidato: Candidato): Set<Avaliacao> {
        return HashSet(daoU.listarTodasDe(candidato))
    }

}


