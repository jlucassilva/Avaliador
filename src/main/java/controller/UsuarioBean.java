package controller;

import model.Usuario;
import service.exception.ServiceException;
import service.UsuarioService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {
    private static final long serialVersionUID = 6658896176906049876L;

    @Inject
    private UsuarioService usuarioService;

    private Usuario usuarioSelecionado;
    private String name;
    private String test;
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {
        usuarios = new ArrayList<>();
        usuarioSelecionado = new Usuario();
    }

    private void carregarUsuario() throws ServiceException {
        usuarios = usuarioService.listarTodos();
    }

    public void salvarOuAtualizar() {
        try {
            usuarioService.salvar(usuarioSelecionado);
            carregarUsuario();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
