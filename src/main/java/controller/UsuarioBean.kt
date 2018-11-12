package controller

import model.Anunciante
import model.Candidato
import model.Competencia
import model.Usuario
import service.AnuncianteService
import service.CandidatoService
import service.CompetenciaService
import service.exception.ServiceException
import java.io.IOException
import java.io.Serializable
import java.util.*
import javax.annotation.PostConstruct
import javax.faces.context.FacesContext
import javax.faces.view.ViewScoped
import javax.inject.Inject
import javax.inject.Named
import kotlin.streams.toList

@Named
@ViewScoped
class UsuarioBean : Serializable {

    @Inject
    private var candidatoService: CandidatoService? = null
    @Inject
    private var competenciaService: CompetenciaService? = null
    @Inject
    private var anuncianteService: AnuncianteService? = null

    var usuarioSelecionado: Usuario? = null
    var usuarios: List<Usuario>? = null
    var competencias: List<Competencia>? = null
    private var competenciasSelecionadas: MutableList<Competencia>? = null

    var anunciante: Boolean? = false

    @PostConstruct
    fun init() {
        usuarios = ArrayList()
        usuarioSelecionado = Usuario()
        atualizarCompetencias()
    }

    private fun atualizarCompetencias() {
        competencias = competenciaService!!.listarTodos()
        competenciasSelecionadas = ArrayList()
        if (!competencias!!.isEmpty())
            competenciasSelecionadas!!.add(competencias!![Random().nextInt(competencias!!.size)])
    }

    fun salvarOuAtualizar() {
        try {
            if (anunciante!!) {
                usuarioSelecionado!!.candidato = null
                val anunciante = Anunciante(usuarioSelecionado!!.nome, usuarioSelecionado!!)
                anuncianteService!!.salvar(anunciante)
            } else {
                val candidato = Candidato()
                candidato.nome = usuarioSelecionado!!.nome
                candidato.usuario = usuarioSelecionado
                candidato.competencias = competenciasSelecionadas!!
                usuarioSelecionado!!.candidato = null

                candidatoService!!.salvar(candidato)
            }

            val ec = FacesContext.getCurrentInstance().externalContext
            ec.redirect(ec.requestContextPath + "/login.xhtml")

        } catch (e: ServiceException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun completaCompetencias(descricao: String): List<Competencia> {
        return competencias!!.stream()
                .filter { competencia ->
                    var c: List<Competencia> = ArrayList()
                    if (usuarioSelecionado!!.candidato != null)
                        c = usuarioSelecionado!!.candidato!!.competencias
                    competencia.descricao.toLowerCase().contains(descricao.toLowerCase()) && !c.contains(competencia)
                }.toList()
    }

    fun getCompetenciasSelecionadas(): List<Competencia>? {
        return competenciasSelecionadas
    }

    fun setCompetenciasSelecionadas(competenciasSelecionadas: MutableList<Competencia>) {
        this.competenciasSelecionadas = competenciasSelecionadas
    }

    companion object {
        private const val serialVersionUID = 6658896176906049876L
    }
}
