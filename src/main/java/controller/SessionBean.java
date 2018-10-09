package controller;

import kontroller.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Random;

@Named
@SessionScoped
public class SessionBean implements Serializable {
	private static final long serialVersionUID = -8298181986140423432L;

	@Inject
	private Usuario usuarioLogado;


	@PostConstruct
	public void init() {
		setUsuarioLogado((Usuario) recuperarDaSessao("usuarioLogado"));
	}

	public void loginUser(Usuario usuario) {
		this.usuarioLogado = usuario;
		adicionarNaSessao("usuarioLogado", this.usuarioLogado);
		adicionarNaSessao("profilePicture", "/resources/omega-layout/images/profile" + (new Random().nextInt(9) + 1) + ".png");
	}

	public void logout() throws IOException {

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void adicionarNaSessao(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}

	public static Object recuperarDaSessao(String key) {
		return getSessionMap().get(key);
	}

	public static Map<String, Object> getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String profilePicture() {
		return (String) recuperarDaSessao("profilePicture");
	}
}
