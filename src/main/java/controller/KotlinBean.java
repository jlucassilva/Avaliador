package controller;

import kontroller.Usuario;
import service.UsuarioService;
import util.MessageUtil;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class KotlinBean implements Serializable {
	private static final long serialVersionUID = 7640837613028276404L;

	private String texto;
	private String username;
	private String password;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private SessionBean session;

	@PostConstruct
	public void init() {
		texto = "Hello World FAKE";
	}

	public void login() throws IOException {
		Usuario usuario = usuarioService.findUserWith(username, password);
		if (usuario == null || usuario.getId() == null) {
			MessageUtil.addErrorMessage("Usuario ou senha não encontrados");
		} else {
			session.loginUser(usuario);
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/home.xhtml");
		}
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
