package dao;

 import model.Candidato;
 import model.Vaga;

import java.io.Serializable;
import java.util.List;

public class VagaDao extends AbstractDao<Vaga> implements Serializable {
	private static final long serialVersionUID = 5318974196807577808L;

	public List<Vaga> obterVagasSemAvaliacaoPelo(Candidato candidato) {
		return getEntityManager().createQuery("select v from Vaga v " +
				"where v not in " +
				"(select va.vaga from Avaliacao va " +
				"where va.candidato = :candidato)", Vaga.class)
				.setParameter("candidato", candidato)
				.getResultList();
	}

}
