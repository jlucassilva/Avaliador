package dao

 import model.Usuario
 import java.io.Serializable
 import javax.persistence.NoResultException
 import javax.persistence.TypedQuery

open class UsuarioDao: AbstractDao<Usuario>(Usuario::class.java),Serializable {
    
    fun findUserWith(username: String, password: String): Usuario {
        return getEntityManager().createQuery("select u from Usuario u" +
                " where u.username = :username" +
                " and   u.password = :password", Usuario::class.java)
                .setParameter("username", username)
                .setParameter("password", password)
                .safeSingleResult()
    }

   fun TypedQuery<Usuario>.safeSingleResult(): model.Usuario {
        return try {
            this.singleResult
        } catch (e: NoResultException) {
            e.printStackTrace()
            Usuario()
        }
    }}