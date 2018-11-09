package model

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
class Competencia : Serializable {
    @Transient
    private val serialVersionUID = -2449149862024727141L

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long? = null
    @Column
    var descricao: String = ""

    constructor()

    constructor(competencia: Competencia) {
        this.id = competencia.id
        this.descricao = competencia.descricao
    }

}