package controller

import intelligence.RankPorVaga
import model.Avaliacao
import model.Candidato
import service.AvaliacaoService
import service.CandidatoService
import service.UsuarioService
import service.VagaService
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

@Named
@ViewScoped
class AvaliacaoBean : Serializable {

    var avaliacao: Avaliacao = Avaliacao()
    var avaliacoes: List<Avaliacao> = ArrayList()
    var avaliacoesFiltradas: List<Avaliacao> = ArrayList()

    private var usuario: SessionBean
    private var vagaService: VagaService
    private var usuarioService: UsuarioService
    private var avaliacaoService: AvaliacaoService
    private var candidatoService: CandidatoService

    @Inject
    constructor(vagaService: VagaService, avaliacaoService: AvaliacaoService, usuario: SessionBean, candidatoService: CandidatoService, usuarioService: UsuarioService) {
        this.usuario = usuario
        this.vagaService = vagaService
        this.usuarioService = usuarioService
        this.avaliacaoService = avaliacaoService
        this.candidatoService = candidatoService
    }

    @PostConstruct
    fun init() {
        if (filtraUsuario()) {
            carregaAvaliacoes()
            carregarCandidatos()
        }
    }

    private fun filtraUsuario(): Boolean {
        if (usuario.usuarioLogado?.candidato == null) {
            try {
                val ec = FacesContext.getCurrentInstance().externalContext
                ec.redirect(ec.requestContextPath + "/vaga.xhtml")
                return false
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return true
    }

    private fun carregarCandidatos() {
        encontraRecomendacoes()
    }

    private fun carregaAvaliacoes() {
        val candidato = usuario.usuarioLogado?.candidato
        avaliacoes = ArrayList(avaliacaoService.listarTodasDe(candidato!!))
        avaliacoesFiltradas = avaliacoes
        filtraAvaliacoes(candidato)
    }

    private fun filtraAvaliacoes(candidato: Candidato) {
        var vagaSemAvaliacao = vagaService.obterVagasSemAvaliacaoPelo(candidato)

        val recomendadas = encontraRecomendacoes()

        vagaSemAvaliacao = vagaSemAvaliacao.subtract(recomendadas.map { it.vaga!! }).toList()

        val porCompetencia: ArrayList<Avaliacao> = ArrayList()

        vagaSemAvaliacao.filter { vaga -> !Collections.disjoint(vaga.competencias, candidato.competencias) }
                .forEach { vaga -> porCompetencia.add(Avaliacao(vaga, candidato)) }

        porCompetencia.addAll(avaliacoes)

        vagaSemAvaliacao = vagaSemAvaliacao.subtract(porCompetencia.map { it.vaga!! }).toList()
        vagaSemAvaliacao.forEach { vaga -> porCompetencia.add(Avaliacao(vaga, candidato)) }


        val temp = avaliacoes.toMutableList()
        temp.clear()
        temp.addAll(recomendadas)
        temp.addAll(porCompetencia)
        avaliacoes = temp.toList()

        avaliacoesFiltradas = avaliacoes

    }

    private fun encontraRecomendacoes(): List<Avaliacao> {
        val principal = usuario.usuarioLogado?.candidato
        principal!!.avaliacoes = avaliacaoService.listarTodasDe(principal)
        val outrosCandidatos = ArrayList<Candidato>()

        candidatoService.listarTodos().forEach { candidato ->
            candidato.avaliacoes = avaliacaoService.listarTodasDe(candidato)
            outrosCandidatos.add(candidato)
        }

        outrosCandidatos.remove(principal)
        val ranking = RankPorVaga().encontraRecomendacoes(principal, outrosCandidatos)

        println(ranking.stream()
                .filter { rankVaga -> rankVaga.similaridade > 3.0 }.count())
        return ranking
                .asSequence()
                .filter { rankVaga -> rankVaga.similaridade > 3.0 }
                .map { rankVaga -> Avaliacao(rankVaga.item!!, principal, rankVaga.similaridade, true) }
                .toList()

    }

    fun avaliando(ava: Avaliacao) {
        try {
            if (ava.recomendada) ava.recomendada = false
            avaliacaoService.atualizar(ava)
        } catch (e: ServiceException) {
            e.printStackTrace()
        }
    }


}