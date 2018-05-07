package dao;

import model.Usuario;

public interface UsuarioDao extends Dao<Usuario> {
    Usuario findUserWith(String username, String password);
}
