package service;

import dao.CandidatoDao;
import model.Candidato;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class CandidatoService extends ServiceAbstract<Candidato> implements Serializable {

	private static final long serialVersionUID = 6892125671661361672L;
	private CandidatoDao dao;

	@Inject
	public CandidatoService(CandidatoDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Candidato> listarTodosOsCandidatos() {
		return dao.listarTodosComAvaliacoes();
	}

}
