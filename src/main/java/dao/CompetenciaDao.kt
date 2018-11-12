package dao

import model.Competencia
import java.io.Serializable

open class CompetenciaDao: AbstractDao<Competencia>(Competencia::class.java), Serializable {
}