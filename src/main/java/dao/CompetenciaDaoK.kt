package dao

import model.Competencia
import java.io.Serializable

open class CompetenciaDaoK: AbstractDaoK<Competencia>(Competencia::class.java), Serializable {
}