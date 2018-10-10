package dao

import org.hibernate.Session
import util.jpa.Transactional
import java.lang.reflect.ParameterizedType
import javax.inject.Inject
import javax.persistence.EntityManager

abstract class AbstractDaoK<T>(persistentClass: Class<T>) : DaoK<T> {

    @Inject
    var manager: EntityManager? = null

    private var entity: Class<T> = persistentClass

    init {
        val genericClass = javaClass.genericSuperclass as Class<*>
        val parameterized = genericClass.genericSuperclass as ParameterizedType
        entity = parameterized.actualTypeArguments[0] as Class<T>
    }

    override fun getEntityManager(): EntityManager {
        return this.manager!!
    }

    override fun getSession(): Session {
        return manager?.delegate  as Session
    }

    override fun listarTodos(): List<T> {
        return manager?.createQuery("from " + entity.simpleName, entity)?.resultList as List<T>
    }

    @Transactional
    override fun salvar(entity: T): T {
        var newEntity = entity
        manager?.persist(entity)
        newEntity = manager?.merge(newEntity)!!

        return newEntity
    }

    @Transactional
    override fun atualizar(entity: T): T {
        return manager?.merge(entity)!!
    }

    @Transactional
    override fun encontraPeloId(entity: T): T {
        return manager?.find(this.entity, entity)!!
    }

    override fun deletar(id: Long?) {
        manager?.remove(id)!!
    }

}