package controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class LogoutBean implements Serializable {
	private static final long serialVersionUID = 1918292994136214163L;

	@Inject
	private SessionBean usuario;

	@PostConstruct
	public void init(){
		try {
			usuario.logout();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String logout() {
		return "";
	}


}
