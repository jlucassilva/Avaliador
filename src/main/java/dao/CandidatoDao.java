package dao;

import model.Candidato;

import java.io.Serializable;
import java.util.List;

public class CandidatoDao extends AbstractDao<Candidato> implements Serializable {
	private static final long serialVersionUID = -6000184569973277478L;


	public List<Candidato> listarTodosComAvaliacoes() {
		return getEntityManager().createQuery("select c from Candidato c ", Candidato.class)
				.getResultList();
	}
}
