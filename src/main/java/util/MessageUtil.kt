package util

import javax.faces.application.FacesMessage
import javax.faces.context.FacesContext

object MessageUtil {
    fun addErrorMessage(message: String) {
        FacesContext.getCurrentInstance().externalContext.flash.isKeepMessages = true

        FacesContext.getCurrentInstance().addMessage(null,
                FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro: $message"))
    }
}
