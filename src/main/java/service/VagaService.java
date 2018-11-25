package service;

import dao.VagaDao;
import model.Candidato;
import model.Vaga;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class VagaService extends ServiceAbstract<Vaga> implements Serializable {

	private static final long serialVersionUID = 8064877996917052899L;
	private VagaDao dao;

	@Inject
	public VagaService(VagaDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Vaga> obterVagasSemAvaliacaoPelo(Candidato candidato) {
		return dao.obterVagasSemAvaliacaoPelo(candidato);
	}
}
