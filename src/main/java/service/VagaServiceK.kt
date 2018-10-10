package service

import dao.VagaDaoK
import model.Candidato
import model.Vaga
import java.io.Serializable
import javax.inject.Inject

class VagaServiceK @Inject constructor(var daoU: VagaDaoK) : Serializable, ServiceAbstractK<Vaga>(dao = daoU) {

    val serialVersionUID = -2800411759544395063L

     fun obterVagasSemAvaliacaoPelo(candidato: Candidato): List<Vaga> {
        return daoU.obterVagasSemAvaliacaoPelo(candidato)
    }
}


