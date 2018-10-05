package controller;

import model.Vaga;
import service.VagaService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class TestandoBean implements Serializable {
	private static final long serialVersionUID = 1918292994136214163L;

	@Inject
	private VagaService service;

	@PostConstruct
	public void init() {

	}



}
