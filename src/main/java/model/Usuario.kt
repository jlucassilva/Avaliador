package model

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
class Usuario : Serializable {
    @Transient
    private val serialVersionUID = 8511735530142157411L

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long? = null

    @Column(unique = true)
    var username: String = ""

    @Column
    var password: String = ""

    @Column
    var nome: String = ""

    @OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
    var candidato: Candidato? = null

    @OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
    var anunciante: Anunciante? = null
}