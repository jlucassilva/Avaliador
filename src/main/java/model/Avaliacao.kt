package model

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*

@Entity
class Avaliacao : Serializable {
    @Transient
    private val serialVersionUID = 5911361446102928715L

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long? = null
    @ManyToOne
    var vaga: Vaga? = null
    @ManyToOne
    var candidato: Candidato? = null
    var nota: Double = 0.0
    @Transient
    var recomendada = false

    constructor()

    constructor(vaga: Vaga, candidato: Candidato) {
        this.vaga = vaga
        this.candidato = candidato
    }

    constructor(id: Long, candidato: Candidato, vaga: Vaga, nota: Double) {
        this.id = id
        this.vaga = vaga
        this.candidato = candidato
        this.nota = nota
    }

    constructor(vaga: Vaga, candidato: Candidato, nota: Double, recomendada: Boolean) {
        this.vaga = vaga
        this.candidato = candidato
        this.nota = nota
        this.recomendada = recomendada
    }

    fun getIdVaga(): Long? {
        return vaga?.id
    }

}