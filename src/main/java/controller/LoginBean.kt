package controller

import service.UsuarioService
import util.MessageUtil
import java.io.IOException
import java.io.Serializable
import javax.faces.context.FacesContext
import javax.faces.view.ViewScoped
import javax.inject.Inject
import javax.inject.Named

@Named
@ViewScoped
class LoginBean : Serializable {
    val serialVersionUID = 501635185433949249L

    @Inject
    private var usuarioServiceK: UsuarioService? = null

    @Inject
    private var session: SessionBean? = null

    var username = ""
    var password = ""

    @Throws(IOException::class)
    fun login() {
        val usuario = usuarioServiceK?.findUserWith(username, password)
        if (usuario == null || usuario.id == null) {
            MessageUtil.addErrorMessage("Usuario ou senha não encontrados")
        } else {
            session!!.loginUser(usuario)
            val ec = FacesContext.getCurrentInstance().externalContext
            ec.redirect(ec.requestContextPath + "/home.xhtml")
        }
    }

}
