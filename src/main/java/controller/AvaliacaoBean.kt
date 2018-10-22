package controller

import intelligence.RankPorVaga
import model.Avaliacao
import model.Candidato
import model.Vaga
import service.AvaliacaoServiceK
import service.CandidatoServiceK
import service.UsuarioServiceK
import service.VagaServiceK
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
    var vagasRecomendadas: List<Vaga> = ArrayList()
    var avaliacoesFiltradas: List<Avaliacao> = ArrayList()

    private var usuario: SessionBean
    private var vagaService: VagaServiceK
    private var usuarioService: UsuarioServiceK
    private var avaliacaoService: AvaliacaoServiceK
    private var candidatoService: CandidatoServiceK

    @Inject
    constructor(vagaService: VagaServiceK, avaliacaoService: AvaliacaoServiceK, usuario: SessionBean, candidatoService: CandidatoServiceK, usuarioService: UsuarioServiceK) {
        this.vagaService = vagaService
        this.avaliacaoService = avaliacaoService
        this.usuario = usuario
        this.candidatoService = candidatoService
        this.usuarioService = usuarioService
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


    }

    private fun encontraRecomendacoes(): List<Avaliacao> {
        val principal = usuario.usuarioLogado?.candidato
        principal!!.avaliacoes = avaliacaoService.listarTodasDe(principal )
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
                .map { rankVaga -> Avaliacao(rankVaga.item!!, principal , rankVaga.similaridade, true) }
                .toList()

    }

    fun avaliando(ava: Avaliacao) {
        try {
            if (ava.recomendada)
                ava.recomendada = false
            avaliacaoService.atualizar(ava)
        } catch (e: ServiceException) {
            e.printStackTrace()
        }
    }


}