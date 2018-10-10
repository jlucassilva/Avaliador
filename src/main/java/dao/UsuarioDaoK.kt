package dao

 import model.Usuario
 import java.io.Serializable

open class UsuarioDaoK: AbstractDaoK<Usuario>(Usuario::class.java),Serializable {

    fun findUserWith(username: String, password: String): Usuario? {
        return getEntityManager().createQuery("select u from Usuario u" +
                " where u.username = :username" +
                " and   u.password = :password", Usuario::class.java)
                .setParameter("username", username)
                .setParameter("password", password)
                .resultList
                .stream()
                .findFirst()
                .orElse(null)
    }
}