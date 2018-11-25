package model

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*

@Entity
class Usuario : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long = 0

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