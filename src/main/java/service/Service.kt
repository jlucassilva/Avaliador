package service

import service.exception.ServiceException

interface Service<T> {
    fun listarTodos(): List<T>

    @Throws(ServiceException::class)
    fun salvar(entity: T): T

    @Throws(ServiceException::class)
    fun atualizar(entity: T): T

    @Throws(ServiceException::class)
    fun encontraPeloId(entity: T): T
}