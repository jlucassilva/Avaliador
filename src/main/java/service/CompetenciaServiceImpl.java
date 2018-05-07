package service;

import dao.CompetenciaDao;
import model.Competencia;

import javax.inject.Inject;
import java.io.Serializable;

public class CompetenciaServiceImpl extends ServiceAbstract<Competencia> implements CompetenciaService, Serializable {

    private static final long serialVersionUID = 6892125675166136672L;
    private CompetenciaDao dao;

    @Inject
    public CompetenciaServiceImpl(CompetenciaDao dao) {
        super(dao);
        this.dao = dao;
    }
}
