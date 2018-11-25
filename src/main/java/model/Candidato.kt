package model

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.io.Serializable
import javax.persistence.*

@Entity
class Candidato : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long = 0L

    var nome: String = ""

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var usuario: Usuario? = null

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    var competencias: List<Competencia> = ArrayList()

    @OneToMany(fetch = FetchType.EAGER)
    var avaliacoes: Set<Avaliacao> = emptySet()

    constructor()

    constructor(nome: String) {
        this.nome = nome
    }

    constructor(id: Long, nome: String) {
        this.nome = nome
        this.id = id
    }

    constructor(id: Long, nome: String, avaliacoes: Set<Avaliacao>) {
        this.id = id
        this.nome = nome
        this.avaliacoes = avaliacoes
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Candidato

        if (id != other.id) return false
        if (nome != other.nome) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nome.hashCode()
        return result
    }


}