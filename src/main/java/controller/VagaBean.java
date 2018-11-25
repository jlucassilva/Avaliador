package controller;

import model.Vaga;
import service.VagaService;
import service.exception.ServiceException;
import util.MessageUtil;
import util.Util;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class VagaBean implements Serializable {
	private static final long serialVersionUID = -1696903758790487986L;

	@Inject
	private VagaService service;

	private List<Vaga> vagas;
	private List<Vaga> vagaFiltrada;

	private Vaga vaga;
	private String tituloModal;

	@PostConstruct
	public void init() {
		tituloModal = "";
		vaga = new Vaga();
		atualizarVagas();
		vagaFiltrada = vagas;
		Util.atualizaComponente("form:tabela");
	}

	private void atualizarVagas() {
		vagas = service.listarTodos();
	}

	public void iniciaCadastro() {
		tituloModal = "Cadastro de Vaga";
		vaga = new Vaga();
	}

	public void iniciaAtualizacao(Vaga vaga) {
		tituloModal = "Atualização de Vaga";
		this.vaga = new Vaga(vaga);
		Util.executaJS("PF('dialog').show();");
	}

	public void salvar() {
		try {
			vaga.setDataDoAnucio(LocalDateTime.now());
			service.atualizar(vaga);
			atualizarVagas();
			Util.executaJS("PF('dialog').hide();");
			MessageUtil.addErrorMessage("Vaga cadastrada com sucesso!");
		} catch (ServiceException e) {
			e.printStackTrace();
			MessageUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<Vaga> getVagas() {
		if (vagas == null) {
			atualizarVagas();
		}
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public String getTituloModal() {
		return tituloModal;
	}

	public void setTituloModal(String tituloModal) {
		this.tituloModal = tituloModal;
	}

	public List<Vaga> getVagaFiltrada() {
		return vagaFiltrada;
	}

	public void setVagaFiltrada(List<Vaga> vagaFiltrada) {
		this.vagaFiltrada = vagaFiltrada;
	}
}
