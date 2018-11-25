package service;

import dao.AnuncianteDao;
import model.Anunciante;

import javax.inject.Inject;
import java.io.Serializable;

public class AnuncianteService extends ServiceAbstract<Anunciante> implements Serializable {

	private static final long serialVersionUID = 6892125675166136672L;
	private AnuncianteDao dao;

	@Inject
	public AnuncianteService(AnuncianteDao dao) {
		super(dao);
		this.dao = dao;
	}
}
