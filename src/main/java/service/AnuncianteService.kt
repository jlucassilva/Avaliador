package service

import dao.AnuncianteDao
import model.Anunciante
import java.io.Serializable
import javax.inject.Inject

class AnuncianteService @Inject constructor(var daoU: AnuncianteDao) : Serializable, AbstractService<Anunciante>(dao = daoU) {
    val serialVersionUID = -2800411759544395063L

}


