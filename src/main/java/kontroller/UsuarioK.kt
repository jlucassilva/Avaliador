package kontroller

import model.Anunciante
import model.Candidato
import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import javax.persistence.*

@Entity
class Usuario : Serializable {

    private val serialVersionUID = 8511735530142157411L

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long? = null

    @Column(unique = true)
    var username: String? = null
    @Column
    var password: String? = null
    @Column
    var nome: String? = null
    @OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
    var candidato: Candidato? = null
    @OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
    var anunciante: Anunciante? = null
}