package model

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*

@Entity
class Competencia : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long = 0L
    @Column
    var descricao: String = ""

    constructor()

    constructor(competencia: Competencia) {
        this.id = competencia.id
        this.descricao = competencia.descricao
    }

}