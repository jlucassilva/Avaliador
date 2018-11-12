import model.Vaga
import service.VagaService
import service.exception.ServiceException
import util.MessageUtil
import util.Util
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.annotation.PostConstruct
import javax.faces.view.ViewScoped
import javax.inject.Inject
import javax.inject.Named

@Named
@ViewScoped
class VagaBean : Serializable {

    @Inject
    private var service: VagaService? = null

    var vagas: List<Vaga> = ArrayList()
    var vagaFiltrada: List<Vaga> = ArrayList()

    var vaga: Vaga = Vaga()
    var tituloModal: String = ""

    @PostConstruct
    fun init() {
        atualizarVagas()
        vagaFiltrada = vagas
        Util.atualizaComponente("form:tabela")
    }

    private fun atualizarVagas() {
        vagas = service!!.listarTodos()
    }

    fun iniciaCadastro() {
        tituloModal = "Cadastro de Vaga"
        vaga = Vaga()
        Util.executaJS("PF('dialog').show();")
    }

    fun iniciaAtualizacao(vaga: Vaga) {
        tituloModal = "Atualização de Vaga"
        this.vaga = Vaga(vaga)
        Util.executaJS("PF('dialog').show();")
    }

    fun salvar() {
        try {
            vaga.dataDoAnucio = LocalDateTime.now()
            service!!.atualizar(vaga)
            atualizarVagas()
            Util.executaJS("PF('dialog').hide();")
            MessageUtil.addErrorMessage("Vaga cadastrada com sucesso!")
        } catch (e: ServiceException) {
            e.printStackTrace()
            MessageUtil.addErrorMessage(e.message!!)
        }

    }

    val serialVersionUID = -1696903758790487986L

}
