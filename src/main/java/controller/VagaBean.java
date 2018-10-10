package controller;

import model.Job;
import model.Vaga;
import org.jsoup.nodes.TextNode;
import service.JobService;
import service.VagaServiceK;
import service.exception.ServiceException;
import util.MessageUtil;
import util.Util;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class VagaBean implements Serializable {
	private static final long serialVersionUID = -1696903758790487986L;

	@Inject
	private VagaServiceK service;

	@Inject
	private JobService jobService;
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

		List<Vaga> tempVagas = new ArrayList<>();
		vagas.forEach(vaga1 -> {
			vaga1.setDescricao(formataDescricao(vaga1.getDescricao()));
			tempVagas.add(vaga1);

		});

		vagas = tempVagas;


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

//    public void excluir() {
//        try {
//            service.deletar(vaga.getId());
//            atualizarCompetencias();
//            MessageUtil.addErrorMessage("Competencia excluido com sucesso!");
//        } catch (ServiceException e) {
//            e.printStackTrace();
//            MessageUtil.addErrorMessage("Não é possivel deletar este item.");
//        }
//    }

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

	public void jobsParaVagas() {
		List<Job> jobs = jobService.listarTodos();
		List<Vaga> vagas = new ArrayList<>();
		jobs.forEach(job -> {
			Vaga vaga = new Vaga();
			vaga.setDataDoAnucio(LocalDateTime.now());
			vaga.setTitulo(job.getTitle());
			vaga.setDescricao(job.getDescription());
			vagas.add(vaga);
		});

		vagas.forEach(vaga -> {
			try {
				service.salvar(vaga);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		});

	}

	public String formataDescricao(String descricao) {
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<p>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</p>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<li>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</li>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<ul>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</ul>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<br>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</br>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<strong>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</strong>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<a(.+?)</a>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<em(.+?)</em>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<i>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</i>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<h4(.+?)</h4>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<h3(.+?)</h3>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<img(.+?)>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<h1>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</h1>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</a>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<hr>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<b>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</b>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<ol>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</ol>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<h2>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</h2>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<code>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</code>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("<pre>", "");
		descricao = TextNode.createFromEncoded(descricao).getWholeText().replaceAll("</pre>", "");
		return descricao;
	}


}
