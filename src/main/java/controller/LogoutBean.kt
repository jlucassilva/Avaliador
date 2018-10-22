package controller

import java.io.IOException
import java.io.Serializable
import javax.annotation.PostConstruct
import javax.faces.view.ViewScoped
import javax.inject.Inject
import javax.inject.Named

@Named
@ViewScoped
class LogoutBean : Serializable {
    val serialVersionUID = 1918292994136214163L

    @Inject
    private var usuario: SessionBean? = null

    @PostConstruct
    fun init() {
        try {
            usuario!!.logout()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun logout(): String {
        return ""
    }


}
