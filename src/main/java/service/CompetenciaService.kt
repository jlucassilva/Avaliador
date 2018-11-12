package service

import dao.CompetenciaDao
import model.Competencia
import java.io.Serializable
import javax.inject.Inject

class CompetenciaService @Inject constructor(var daoU: CompetenciaDao) : Serializable, AbstractService<Competencia>(dao = daoU) {
    val serialVersionUID = -2800411759544395063L
}


