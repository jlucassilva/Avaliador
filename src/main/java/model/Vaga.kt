package model

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.io.Serializable
import java.time.LocalDateTime
import java.util.ArrayList
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
class Vaga : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long? = null
    var titulo: String? = null
    @Lob
    @Column(columnDefinition = "text")
    var descricao: String? = null
    var salario: Double = 0.toDouble()
    @ManyToOne(fetch = FetchType.LAZY)
    var anunciante: Anunciante? = null
    var dataDoAnucio: LocalDateTime? = null
    @OneToMany(fetch = FetchType.LAZY)
    var avaliacoes: List<Avaliacao>? = null
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    var competencias: List<Competencia> = ArrayList()

    constructor()

    constructor(id: Long?, titulo: String) {
        this.id = id
        this.titulo = titulo
    }

    constructor(vaga: Vaga) {
        this.id = vaga.id
        this.titulo = vaga.titulo
        this.descricao = vaga.descricao
        this.salario = vaga.salario
        this.dataDoAnucio = vaga.dataDoAnucio
        this.competencias = vaga.competencias
    }


}