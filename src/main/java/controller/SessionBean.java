package controller;

import model.Usuario;
import service.UsuarioService;

import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionBean implements Serializable {
    private static final long serialVersionUID = -8298181986140423432L;

    @Inject
    private UsuarioService usuarioService;

    private Usuario usuarioLogado;

    public void loginUser(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public void logout() throws IOException {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

}
