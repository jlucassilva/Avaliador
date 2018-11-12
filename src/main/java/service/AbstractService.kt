package service

import dao.Dao
import service.exception.ServiceException

abstract class AbstractService<T>(var dao: Dao<T>) : Service<T> {

    override fun listarTodos(): List<T> {
        return dao.listarTodos()
    }

    @Throws(ServiceException::class)
    override fun salvar(entity: T): T {
        return dao.salvar(entity)
    }

    @Throws(ServiceException::class)
    override fun atualizar(entity: T): T {
        return dao.atualizar(entity)
    }

    @Throws(ServiceException::class)
    override fun encontraPeloId(entity: T): T {
        return dao.encontraPeloId(entity)
    }

}