package controller

 import model.Usuario
 import java.io.IOException
import java.io.Serializable
import java.util.*
import javax.annotation.PostConstruct
import javax.faces.bean.SessionScoped
import javax.faces.context.FacesContext
import javax.inject.Inject
import javax.inject.Named

@Named
@SessionScoped
class SessionBeanK : Serializable {
    val serialVersionUID = -8298181986140423432L

    @Inject
    private var usuarioLogado: Usuario? = null


    @PostConstruct
    fun init() {
        val temp: Any? = recuperarDaSessao("usuarioLogado")
        usuarioLogado = if (temp != null) temp as Usuario else null
    }

    fun loginUser(usuario: Usuario) {
        this.usuarioLogado = usuario
        adicionarNaSessao("usuarioLogado", this.usuarioLogado)
        adicionarNaSessao("profilePicture", "/resources/omega-layout/images/profile" + (Random().nextInt(9) + 1) + ".png")
    }

    @Throws(IOException::class)
    fun logout() {

        val ec = FacesContext.getCurrentInstance().externalContext
        ec.invalidateSession()
        ec.redirect(ec.requestContextPath + "/login.xhtml")
    }

    fun adicionarNaSessao(key: String, value: Any?) {
        FacesContext.getCurrentInstance().externalContext.sessionMap[key] = value
    }

    fun recuperarDaSessao(key: String): Any? {
        return getSessionMap()[key]
    }

    fun getSessionMap(): Map<String, Any> {
        return FacesContext.getCurrentInstance().externalContext.sessionMap
    }

    fun profilePicture(): String {
        return recuperarDaSessao("profilePicture") as String
    }
}
