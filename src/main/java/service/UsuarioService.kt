package service

import dao.UsuarioDao
import model.Usuario
import java.io.Serializable
import javax.inject.Inject

class UsuarioService @Inject constructor(var daoU: UsuarioDao) : Serializable, AbstractService<Usuario>(dao = daoU) {

    val serialVersionUID = -2800411759544395063L

    fun findUserWith(username: String, password: String): Usuario? {
        return daoU.findUserWith(username, password)
    }
}


