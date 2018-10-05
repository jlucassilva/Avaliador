package dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

public interface Dao<T> {

	EntityManager getEntityManager();

	Session getSession();

	List<T> listarTodos();

	T salvar(T entity);

	T atualizar(T entity);

	T encontraPeloId(T entity);

	void deletar(Long id);
}
