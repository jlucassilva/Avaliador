package service;

import dao.Dao;
import service.exception.ServiceException;

import java.util.List;

public class ServiceAbstract<T> implements Service<T> {

	public Dao dao;

	ServiceAbstract(Dao dao) {
		this.dao = dao;
	}


	@Override
	public List<T> listarTodos() {
		return dao.listarTodos();
	}

	@Override
	public T salvar(T entity) throws ServiceException {
		return (T) dao.salvar(entity);
	}

	@Override
	public T atualizar(T entity) throws ServiceException {
		return (T) dao.atualizar(entity);
	}

	@Override
	public T encontraPeloId(T entity) throws ServiceException {
		return (T) dao.encontraPeloId(entity);
	}

}
