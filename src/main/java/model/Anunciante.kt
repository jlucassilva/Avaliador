package model

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*

@Entity
class Anunciante : Serializable {

    @Transient
    private val serialVersionUID = 5911361446123528715L


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long? = null
    var nome: String = ""
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var usuario: Usuario? = null
    @OneToMany(fetch = FetchType.EAGER)
    var vagas: List<Vaga>? = ArrayList()

    constructor()

    constructor(nome: String, usuario: Usuario) {
        this.nome = nome
        this.usuario = usuario
    }

}