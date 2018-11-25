package service;

import dao.CompetenciaDao;
import model.Competencia;

import javax.inject.Inject;
import java.io.Serializable;

public class CompetenciaService extends ServiceAbstract<Competencia> implements Serializable {
	private static final long serialVersionUID = 6892125675166136672L;

	private CompetenciaDao dao;

	@Inject
	public CompetenciaService(CompetenciaDao dao) {
		super(dao);
		this.dao = dao;
	}
}
