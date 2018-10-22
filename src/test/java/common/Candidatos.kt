package common

import model.Candidato

enum class Candidatos constructor(private val id: Long?, val nome: String, candidato: Candidato) {

    Rose(1L, "Lisa Rose", Candidato()),
    Seymour(2L, "Gene Seymour", Candidato()),
    Phillips(3L, "Michael Phillips", Candidato()),
    Puig(4L, "Claudia Puig", Candidato()),
    LaSalle(5L, "Mick LaSalle", Candidato()),
    Matthews(6L, "Jack Matthews", Candidato()),
    Toby(7L, "Toby", Candidato());

    val candidato: Candidato = Candidato(this.id, this.nome)

}
