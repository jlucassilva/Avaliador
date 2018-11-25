package dao;

import util.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {
	private final Class<T> entity;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	AbstractDao() {
		Class<T> genericClass = (Class<T>) getClass().getGenericSuperclass();
		ParameterizedType parameterized = (ParameterizedType) genericClass.getGenericSuperclass();
		entity = (Class<T>) parameterized.getActualTypeArguments()[0];
	}

	public AbstractDao(final Class<T> persistentClass) {
		super();
		this.entity = persistentClass;
	}

	protected Class<T> getEntity() {
		return entity;
	}

	@Override
	public EntityManager getEntityManager() {
		return this.manager;
	}

	@Override
	public List<T> listarTodos() {
		return manager.createQuery("from " + entity.getSimpleName(), entity)
				.getResultList();
	}

	@Override
	@Transactional
	public T salvar(T entity) {
		manager.persist(entity);
		entity = manager.merge(entity);

		return entity;
	}

	@Override
	@Transactional
	public T atualizar(T entity) {
		return manager.merge(entity);
	}

	@Override
	@Transactional
	public T encontraPeloId(T entity) {
		return manager.find(this.entity, entity);
	}

}
