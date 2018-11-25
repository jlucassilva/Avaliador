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
import kotlin.collections.ArrayList
import kotlin.streams.toList

@Named
@ViewScoped
class UsuarioBean : Serializable {

    @Inject
    private lateinit var candidatoService: CandidatoService
    @Inject
    private lateinit var competenciaService: CompetenciaService
    @Inject
    private lateinit var anuncianteService: AnuncianteService

    var usuarioSelecionado: Usuario = Usuario()
    var usuarios: List<Usuario> = emptyList()
    var competencias: List<Competencia> = emptyList()
    private var competenciasSelecionadas: MutableList<Competencia> = ArrayList()

    var anunciante: Boolean? = false

    @PostConstruct
    fun init() {
        usuarios = ArrayList()
        usuarioSelecionado = Usuario()
        atualizarCompetencias()
    }

    private fun atualizarCompetencias() {
        competencias = competenciaService.listarTodos()
        competenciasSelecionadas = ArrayList()
        if (!competencias.isEmpty())
            competenciasSelecionadas.add(competencias[Random().nextInt(competencias.size)])
    }

    fun salvarOuAtualizar() {
        try {
            if (anunciante!!) {
                val anunciante = Anunciante(usuarioSelecionado.nome, usuarioSelecionado)
                anuncianteService.salvar(anunciante)
            } else {
                val candidato = Candidato()
                candidato.nome = usuarioSelecionado.nome
                candidato.usuario = usuarioSelecionado
                candidato.competencias = competenciasSelecionadas
                usuarioSelecionado.candidato = null

                candidatoService.salvar(candidato)
            }

            val ec = FacesContext.getCurrentInstance().externalContext
            ec.redirect(ec.requestContextPath + "/login.xhtml")

        } catch (e: ServiceException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    val usuario: Usuario = usuarioSelecionado
    fun completaCompetencias(query: String): List<Competencia> {
        return competencias.filter { competencia ->

            var comp: List<Competencia> = usuarioSelecionado.candidato!!.competencias
            competencia.descricao.contains(query, true)
                    && comp.contains(competencia)
        }.toList()
    }


    fun encontraNovaCompetencia(query: String): List<Competencia> {
        return competencias.filter { competencia ->
            val selecionadas: List<Competencia> =
                    usuario.candidato?.competencias ?: ArrayList()

            competencia.descricao.contains(query, true)
                    && !selecionadas.contains(competencia)
        }.toList()
    }

    fun completaCompetencias1(query: String): List<Competencia> {
        return competencias.filter { competencia ->
            val selecionadas: List<Competencia> =
                    usuario.candidato?.competencias ?: ArrayList()

            competencia.descricao.contains(query, true)
                    && !selecionadas.contains(competencia)
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
