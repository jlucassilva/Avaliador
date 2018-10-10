package service

import dao.CompetenciaDaoK
import dao.UsuarioDaoK
import model.Competencia
import model.Usuario
import java.io.Serializable
import javax.inject.Inject

class CompetenciaServiceK @Inject constructor(var daoU: CompetenciaDaoK) : Serializable, ServiceAbstractK<Competencia>(dao = daoU) {
    val serialVersionUID = -2800411759544395063L
}


