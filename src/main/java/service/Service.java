package service;

import service.exception.ServiceException;

import java.util.List;

public interface Service<T> {
    List<T> listarTodos();

    T salvar(T entity) throws ServiceException;

    T atualizar(T entity) throws ServiceException;

    T encontraPeloId(T entity) throws ServiceException;
}
