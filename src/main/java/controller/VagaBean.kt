import model.Vaga
import org.jsoup.nodes.TextNode
import service.VagaServiceK
import service.exception.ServiceException
import util.MessageUtil
import util.Util
import java.io.Serializable
import java.time.LocalDateTime
import java.util.ArrayList
import javax.annotation.PostConstruct
import javax.faces.view.ViewScoped
import javax.inject.Inject
import javax.inject.Named

@Named
@ViewScoped
class VagaBean : Serializable {

    @Inject
    private var service: VagaServiceK? = null

    private var vagas: List<Vaga> = ArrayList()
    private var vagaFiltrada: List<Vaga> = ArrayList()

    var vaga: Vaga? = null
    private var tituloModal: String = ""

    @PostConstruct
    fun init() {
        vaga = Vaga()
        atualizarVagas()
        vagaFiltrada = vagas
        Util.atualizaComponente("form:tabela")
    }

    private fun atualizarVagas() {
        vagas = service!!.listarTodos()

        val tempVagas = ArrayList<Vaga>()
        vagas.forEach { vaga1 ->
            vaga1.descricao = formataDescricao(vaga1.descricao)
            tempVagas.add(vaga1)

        }

        vagas = tempVagas
    }

    fun iniciaCadastro() {
        tituloModal = "Cadastro de Vaga"
        vaga = Vaga()
    }

    fun iniciaAtualizacao(vaga: Vaga) {
        tituloModal = "Atualização de Vaga"
        this.vaga = Vaga(vaga)
        Util.executaJS("PF('dialog').show();")
    }

    fun salvar() {
        try {
            vaga!!.dataDoAnucio = LocalDateTime.now()
            service!!.atualizar(vaga!!)
            atualizarVagas()
            Util.executaJS("PF('dialog').hide();")
            MessageUtil.addErrorMessage("Vaga cadastrada com sucesso!")
        } catch (e: ServiceException) {
            e.printStackTrace()
            MessageUtil.addErrorMessage(e.message)
        }

    }

    fun getVagas(): List<Vaga>? {
        if (vagas.isEmpty()) atualizarVagas()
        return vagas
    }


    fun formataDescricao(desc: String?): String {
        var descricao = desc
        descricao = TextNode.createFromEncoded(descricao!!).wholeText.replace("<p>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</p>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<li>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</li>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<ul>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</ul>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<br>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</br>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<strong>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</strong>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<a(.+?)</a>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<em(.+?)</em>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<i>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</i>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<h4(.+?)</h4>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<h3(.+?)</h3>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<img(.+?)>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<h1>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</h1>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</a>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<hr>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<b>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</b>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<ol>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</ol>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<h2>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</h2>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<code>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</code>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("<pre>".toRegex(), "")
        descricao = TextNode.createFromEncoded(descricao).wholeText.replace("</pre>".toRegex(), "")
        return descricao
    }

           val serialVersionUID = -1696903758790487986L

}
