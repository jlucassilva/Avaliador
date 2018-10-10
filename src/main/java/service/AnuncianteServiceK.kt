package service

import dao.AnuncianteDaoK
import dao.UsuarioDaoK
import model.Anunciante
import model.Usuario
import java.io.Serializable
import javax.inject.Inject

class AnuncianteServiceK @Inject constructor(var daoU: AnuncianteDaoK) : Serializable, ServiceAbstractK<Anunciante>(dao = daoU) {
    val serialVersionUID = -2800411759544395063L

}


