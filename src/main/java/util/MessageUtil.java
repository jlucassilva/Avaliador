package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtil {
    public static void addErrorMessage(String message) {
        message = "Erro: " + message;

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "", message));
    }
}
