package dao;

import kontroller.Usuario;
import java.io.Serializable;

public class UsuarioDao extends AbstractDao<Usuario> implements Serializable {
	private static final long serialVersionUID = -118468822692102503L;

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
