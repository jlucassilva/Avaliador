package kontroller

import service.exception.ServiceException

abstract class ServiceAbstractK<T>(var dao: DaoK<T>) : ServiceK<T> {

    override fun listarTodos(): List<T> {
        return dao.listarTodos()
    }

    @Throws(ServiceException::class)
    override fun salvar(entity: T): T {
        return dao.salvar(entity) as T
    }

    @Throws(ServiceException::class)
    override fun atualizar(entity: T): T {
        return dao.atualizar(entity) as T
    }

    @Throws(ServiceException::class)
    override fun encontraPeloId(entity: T): T {
        return dao.encontraPeloId(entity) as T
    }

    @Throws(ServiceException::class)
    override fun deletar(id: Long?) {
        dao.deletar(id)
    }


}