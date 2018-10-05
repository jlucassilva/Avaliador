package kontroller

import org.hibernate.Session
import javax.persistence.EntityManager

interface DaoK <T> {
    fun getEntityManager(): EntityManager

    fun getSession(): Session

    fun listarTodos(): List<T>

    fun salvar(entity: T): T

    fun atualizar(entity: T): T

    fun encontraPeloId(entity: T): T

    fun deletar(id: Long?)

}