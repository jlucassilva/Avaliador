package service

import dao.VagaDao
import model.Candidato
import model.Vaga
import java.io.Serializable
import javax.inject.Inject

class VagaService @Inject constructor(var daoU: VagaDao) : Serializable, AbstractService<Vaga>(dao = daoU) {

    val serialVersionUID = -2800411759544395063L

     fun obterVagasSemAvaliacaoPelo(candidato: Candidato): List<Vaga> {
        return daoU.obterVagasSemAvaliacaoPelo(candidato)
    }
}


