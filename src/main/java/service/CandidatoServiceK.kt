package service

import dao.CandidatoDaoK
import dao.UsuarioDaoK
import model.Candidato
import model.Usuario
import java.io.Serializable
import javax.inject.Inject

class CandidatoServiceK @Inject constructor(var daoU: CandidatoDaoK) : Serializable, ServiceAbstractK<Candidato>(dao = daoU) {

    val serialVersionUID = -2800411759544395063L

    fun listarTodosOsCandidatos(): List<Candidato> {
        return daoU.listarTodosComAvaliacoes()
    }

}


