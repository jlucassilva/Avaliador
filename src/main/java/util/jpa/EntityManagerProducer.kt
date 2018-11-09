package util.jpa

import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Disposes
import javax.enterprise.inject.Produces
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

@ApplicationScoped
class EntityManagerProducer {

    private val factory: EntityManagerFactory = Persistence.createEntityManagerFactory("avaliador")

    @Produces
    @RequestScoped
    fun createEntityManager(): EntityManager {
        return factory.createEntityManager()
    }

    fun closeEntityManager(@Disposes manager: EntityManager) {
        manager.close()
    }
}
