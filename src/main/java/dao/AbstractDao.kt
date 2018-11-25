package dao

import org.hibernate.Session
import util.jpa.Transactional
import javax.inject.Inject
import javax.persistence.EntityManager

abstract class AbstractDao<T>(persistentClass: Class<T>) : Dao<T> {

    @Inject
    lateinit var manager: EntityManager

    private var entity: Class<T> = persistentClass

    override fun getEntityManager(): EntityManager {
        return this.manager
    }

    override fun getSession(): Session {
        return manager.delegate as Session
    }

    override fun listarTodos(): List<T> {
        return manager.createQuery("from " + entity.simpleName, entity)?.resultList as List<T>
    }

    @Transactional
    override fun salvar(entity: T): T {
        var newEntity = entity
        manager.persist(entity)
        newEntity = manager.merge(newEntity)

        return newEntity
    }

    @Transactional
    override fun atualizar(entity: T): T {
        return manager.merge(entity)!!
    }

    @Transactional
    override fun encontraPeloId(entity: T): T {
        return manager.find(this.entity, entity)!!
    }
}