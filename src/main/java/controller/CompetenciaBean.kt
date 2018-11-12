package controller

import model.Competencia
import service.CompetenciaService
import service.exception.ServiceException
import util.MessageUtil
import util.Util
import java.io.Serializable
import javax.annotation.PostConstruct
import javax.faces.view.ViewScoped
import javax.inject.Inject
import javax.inject.Named

@Named
@ViewScoped
class CompetenciaBean : Serializable {

    @Inject
    private lateinit var service: CompetenciaService

    var competencias: List<Competencia> = ArrayList()
    var competencia: Competencia = Competencia()
    var tituloModal: String = ""
    @Inject
    private var servicea: CompetenciaService? = null

    @PostConstruct
    fun init() {
        atualizarCompetencias()
    }

    private fun atualizarCompetencias() {
        competencias = service.listarTodos()
    }

    fun iniciaCadastro() {
        tituloModal = "Cadastro de Competencia"
        competencia = Competencia()
    }

    fun iniciaAtualizacao() {
        tituloModal = "Atualização de Competencia"
        competencia = Competencia(competencia)
    }

    fun salvar() {
        try {
            service.atualizar(competencia)
            atualizarCompetencias()
            Util.executaJS("PF('dialog').hide();")
            MessageUtil.addErrorMessage("Competencia salvo com sucesso!")
        } catch (e: ServiceException) {
            e.printStackTrace()
            MessageUtil.addErrorMessage(e.message!!)
        }
    }

    companion object {
        private const val serialVersionUID = -1696903758790487986L
    }
}