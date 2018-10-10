package service

import dao.AvaliacaoDaoK
import dao.UsuarioDaoK
import model.Avaliacao
import model.Candidato
import model.Usuario
import java.io.Serializable
import javax.inject.Inject

class AvaliacaoServiceK @Inject constructor(var daoU: AvaliacaoDaoK) : Serializable, ServiceAbstractK<Avaliacao>(dao = daoU) {

    val serialVersionUID = -2800411759544395063L

    fun listarTodasDe(candidato: Candidato): Set<Avaliacao> {
        return HashSet(daoU.listarTodasDe(candidato))
    }

}


