package util.jpa

import org.hibernate.exception.ConstraintViolationException
import service.exception.ServiceException
import java.io.Serializable
import javax.annotation.Priority
import javax.inject.Inject
import javax.interceptor.AroundInvoke
import javax.interceptor.Interceptor
import javax.interceptor.InvocationContext
import javax.persistence.EntityManager
import javax.persistence.PersistenceException

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION + 1)
class TransactionInterceptor : Serializable {
    @Inject
    var manager: EntityManager? = null

    @AroundInvoke
    @Throws(Exception::class)
    operator fun invoke(context: InvocationContext): Any {
        val trx = manager!!.transaction

        var criador = false

        try {
            if (!trx!!.isActive) {
                trx.begin()
                trx.rollback()

                trx.begin()
                criador = true
            }
            return context.proceed()
        } catch (e: Exception) {
            if (trx != null && criador) {
                trx.rollback()
            }

            if (e.cause is ConstraintViolationException) {
                throw ServiceException(e.message!!)
            }

            throw e
        } finally {
            try {
                if (trx != null && trx.isActive && criador) {
                    trx.commit()
                }
            } catch (e: PersistenceException) {
                if (e.cause is ConstraintViolationException) {
                    throw ServiceException("Não Foi possivel efetuar o commit!")
                }
            }
        }
    }

    companion object {
        private const val serialVersionUID = 1L
    }

}