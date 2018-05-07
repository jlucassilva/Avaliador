package service;

import model.Usuario;

public interface UsuarioService extends Service<Usuario> {
    Usuario findUserWith(String username, String password);
}
