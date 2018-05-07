package dao;

import model.Usuario;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class UsuarioDaoImpl extends AbstractDao<Usuario> implements UsuarioDao, Serializable {
    private static final long serialVersionUID = -118468822692102503L;

    @Override
    public Usuario findUserWith(String username, String password) {
        return getEntityManager().createQuery("select u from Usuario u" +
                " where u.username = :username" +
                " and   u.password = :password", Usuario.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
