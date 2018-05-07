package util.jpa;

import org.hibernate.exception.ConstraintViolationException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import service.exception.ServiceException;

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION + 1)
public class TransactionInterceptor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private  EntityManager manager;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction trx = manager.getTransaction();

        boolean criador = false;

        try {
            if (!trx.isActive()) {
                // truque para fazer rollback no que já passou
                // (senão, um futuro commit, confirmaria até mesmo
                // operações sem transação)
                trx.begin();
                trx.rollback();
                // agora sim inicia a transação
                trx.begin();
                criador = true;
            }
            return context.proceed();
        } catch (Exception e) {
            if (trx != null && criador) {
                trx.rollback();
            }

            if(e.getCause() instanceof ConstraintViolationException){
                throw new ServiceException(e.getCause().getCause().getMessage());
            }

            throw e;
        } finally {
            try{
                if (trx != null && trx.isActive() && criador) {
                    trx.commit();
                }
            }catch(PersistenceException e){
                if(e.getCause().getCause() instanceof ConstraintViolationException){
                    throw new ServiceException("Não Foi possivel efetuar o commit!");
                }
            }
        }
    }

}
