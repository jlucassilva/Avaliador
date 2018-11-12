package service

import dao.CandidatoDao
import model.Candidato
import java.io.Serializable
import javax.inject.Inject

class CandidatoService @Inject constructor(var daoU: CandidatoDao) : Serializable, AbstractService<Candidato>(dao = daoU) {

    val serialVersionUID = -2800411759544395063L

    fun listarTodosOsCandidatos(): List<Candidato> {
        return daoU.listarTodosComAvaliacoes()
    }

}


