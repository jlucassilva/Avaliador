package kontroller

import service.exception.ServiceException

interface ServiceK<T> {

    abstract fun listarTodos(): List<T>

    @Throws(ServiceException::class)
    abstract fun salvar(entity: T): T

    @Throws(ServiceException::class)
    abstract fun atualizar(entity: T): T

    @Throws(ServiceException::class)
    abstract fun encontraPeloId(entity: T): T

    @Throws(ServiceException::class)
    abstract fun deletar(id: Long?)

}