package dao;

import model.Avaliacao;
import model.Candidato;

import java.io.Serializable;
import java.util.List;

public class AvaliacaoDao extends AbstractDao<Avaliacao> implements Serializable {
	private static final long serialVersionUID = -5626300937923815145L;

	public List<Avaliacao> listarTodasDe(Candidato candidato) {
		return getEntityManager().createQuery("select a from Avaliacao a " +
				"where a.candidato = :candidato", Avaliacao.class)
				.setParameter("candidato", candidato)
				.getResultList();
	}


}
