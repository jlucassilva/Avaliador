package service;

import service.exception.ServiceException;

import java.util.List;

public interface Service<T> {
    List<T> listarTodos();

    void salvar(T entity) throws ServiceException;

    T atualizar(T entity) throws ServiceException;

    T encontraPeloId(T entity) throws ServiceException;

    void deletar(Long id) throws ServiceException;
}
