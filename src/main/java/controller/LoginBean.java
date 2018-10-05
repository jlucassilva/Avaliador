package controller;

import model.Competencia;
import model.Usuario;
import model.Vaga;
import service.CompetenciaService;
import service.UsuarioService;
import service.VagaService;
import service.exception.ServiceException;
import util.MessageUtil;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 501635185433949249L;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private SessionBean session;

	@Inject
	private CompetenciaService competenciaService;

	@Inject
	private VagaService vagaService;

	private String username;
	private String password;

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

//	public void teste() throws ServiceException {
//		List<Vaga> vagas = vagaService.listarTodos();
//		List<Competencia> competencias = competenciaService.listarTodos();
//
//		for (Competencia comp : competencias) {
//			if (comp.getDescricao() == "Java") {
//				List<Vaga> vagas1 = vagas.stream().filter(vaga -> vaga.getDescricao().toLowerCase().contains("java")).collect(Collectors.toList());
//
//				if (vagas1.size() >= 200) {
//					for (int i = 0; i < 20; i++) {
//						Vaga vaga = vagas1.get(i);
//						List<Competencia> comps = new ArrayList<>();
//						comps.add(comp);
//						vaga.setCompetencias(comps);
//
//						vagaService.atualizar(vaga);
//					}
//				} else {
//					for (int i = 0; i < vagas1.size(); i++) {
//						Vaga vaga = vagas1.get(i);
//						List<Competencia> comps = new ArrayList<>();
//						comps.add(comp);
//						vaga.setCompetencias(comps);
//
//						vagaService.atualizar(vaga);
//					}
//				}
//
//			}
//		}
//	}
}

