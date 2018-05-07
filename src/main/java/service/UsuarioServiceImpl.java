package service;

import dao.UsuarioDao;
import model.Usuario;
import service.exception.ServiceException;

import javax.inject.Inject;
import java.io.Serializable;

public class UsuarioServiceImpl extends ServiceAbstract<Usuario> implements UsuarioService, Serializable {

    private static final long serialVersionUID = -2800411759544395063L;
    private UsuarioDao dao;

    @Inject
    public UsuarioServiceImpl(UsuarioDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public Usuario findUserWith(String username, String password) {
        return dao.findUserWith(username, password);
    }
}
