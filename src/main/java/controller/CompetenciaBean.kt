package controller

import model.Competencia
import service.CompetenciaServiceK
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
    private var service: CompetenciaServiceK? = null


    var competencias: List<Competencia>? = null
        get() {
            if (field == null) {
                atualizarCompetencias()
            }
            return field
        }

    var competencia: Competencia? = null

    var tituloModal: String? = null

    @PostConstruct
    fun init() {
        tituloModal = ""
        competencia = Competencia()
    }

    private fun atualizarCompetencias() {
        this.competencias = service!!.listarTodos()
    }

    fun iniciaCadastro() {
        tituloModal = "Cadastro de Competencia"
        competencia = Competencia()
    }

    fun iniciaAtualizacao() {
        tituloModal = "Atualização de Competencia"
        competencia = Competencia(competencia!!)
    }

    fun salvar() {
        try {
            service!!.atualizar(competencia!!)
            atualizarCompetencias()
            Util.executaJS("PF('dialog').hide();")
            MessageUtil.addErrorMessage("Competencia salvo com sucesso!")
        } catch (e: ServiceException) {
            e.printStackTrace()
            MessageUtil.addErrorMessage(e.message)
        }

    }

    fun excluir() {
        try {
            service!!.deletar(competencia!!.id)
            atualizarCompetencias()
            MessageUtil.addErrorMessage("Competencia excluido com sucesso!")
        } catch (e: ServiceException) {
            e.printStackTrace()
            MessageUtil.addErrorMessage("Não é possivel deletar este item.")
        }

    }

    companion object {
        private const val serialVersionUID = -1696903758790487986L
    }
 }
