package kontroller

import model.Usuario
import java.io.Serializable
import javax.inject.Inject

class UsuarioServiceK @Inject constructor(var daoU: UsuarioDaoK) : Serializable, ServiceAbstractK<Usuario>(dao = daoU) {

    val serialVersionUID = -2800411759544395063L

    fun findUserWith(username: String, password: String): Usuario? {
        return daoU.findUserWith(username, password)
    }
}


