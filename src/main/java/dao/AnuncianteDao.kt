package dao

import model.Anunciante
import java.io.Serializable

open class AnuncianteDao: AbstractDao<Anunciante>(Anunciante::class.java), Serializable {
}