package dao

import model.Anunciante
import java.io.Serializable

open class AnuncianteDaoK: AbstractDaoK<Anunciante>(Anunciante::class.java), Serializable {
}