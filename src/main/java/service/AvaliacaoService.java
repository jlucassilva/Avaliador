package service;

import dao.AvaliacaoDao;
import model.Avaliacao;
import model.Candidato;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AvaliacaoService extends ServiceAbstract<Avaliacao> implements Serializable {
	private static final long serialVersionUID = -2090901788926650331L;

	private AvaliacaoDao dao;

	@Inject
	public AvaliacaoService(AvaliacaoDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Set<Avaliacao> listarTodasDe(Candidato candidato){
		return  new HashSet<>(dao.listarTodasDe(candidato));
	}
}
