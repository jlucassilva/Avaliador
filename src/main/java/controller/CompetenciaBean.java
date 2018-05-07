package controller;

import model.Competencia;
import service.CompetenciaService;
import service.exception.ServiceException;
import util.MessageUtil;
import util.Util;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CompetenciaBean implements Serializable {
    private static final long serialVersionUID = -1696903758790487986L;

    @Inject
    private CompetenciaService service;

    private List<Competencia> competencias;

    private Competencia competencia;

    private String tituloModal;

    @PostConstruct
    public void init() {
        tituloModal = "";
        competencia = new Competencia();
    }

    private void atualizarCompetencias() {
        competencias = service.listarTodos();
    }

    public void iniciaCadastro() {
        tituloModal = "Cadastro de Competencia";
        competencia = new Competencia();
    }

    public void iniciaAtualizacao() {
        tituloModal = "Atualização de Competencia";
        competencia = new Competencia(competencia);
    }

    public void salvar() {
        try {
            service.atualizar(competencia);
            atualizarCompetencias();
            Util.executaJS("PF('dialog').hide();");
            MessageUtil.addErrorMessage("Competencia salvo com sucesso!");
        } catch (ServiceException e) {
            e.printStackTrace();
            MessageUtil.addErrorMessage(e.getMessage());
        }
    }

    public void excluir() {
        try {
            service.deletar(competencia.getId());
            atualizarCompetencias();
            MessageUtil.addErrorMessage("Competencia excluido com sucesso!");
        } catch (ServiceException e) {
            e.printStackTrace();
            MessageUtil.addErrorMessage("Não é possivel deletar este item.");
        }
    }

    public List<Competencia> getCompetencias() {
        if (competencias == null) {
            atualizarCompetencias();
        }
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    public Competencia getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Competencia competencia) {
        this.competencia = competencia;
    }

    public String getTituloModal() {
        return tituloModal;
    }

    public void setTituloModal(String tituloModal) {
        this.tituloModal = tituloModal;
    }
}
