package service;

import dao.UsuarioDao;
import kontroller.Usuario;
import service.exception.ServiceException;

import javax.inject.Inject;
import java.io.Serializable;

public class UsuarioService extends ServiceAbstract<Usuario> implements Serializable {

    private static final long serialVersionUID = -2800411759544395063L;
    private UsuarioDao dao;

    @Inject
    public UsuarioService(UsuarioDao dao) {
        super(dao);
        this.dao = dao;
    }

    public Usuario findUserWith(String username, String password) {
        return dao.findUserWith(username, password);
    }
}
