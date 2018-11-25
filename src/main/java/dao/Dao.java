package dao;

import javax.persistence.EntityManager;
import java.util.List;

public interface Dao<T> {

	EntityManager getEntityManager();

	List<T> listarTodos();

	T salvar(T entity);

	T atualizar(T entity);

	T encontraPeloId(T entity);
}
