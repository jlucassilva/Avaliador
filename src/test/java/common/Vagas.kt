package common

import model.Vaga


enum class Vagas private constructor(val id: Long?, val title: String, vaga: Vaga) {

    LADY_WATER(1L, "Lady in the Water", Vaga()),
    SNAKES_ON_A_PLANE(2L, "Snakes on a Plane", Vaga()),
    JUST_MY_LUCK(3L, "Just My Luck", Vaga()),
    SUPERMAN_RETURNS(4L, "Superman Returns", Vaga()),
    NIGHT_LISTENER(5L, "The Night Listener", Vaga()),
    YOU_ME_DUPREE(6L, "You, Me and Dupree", Vaga());

    val vaga: Vaga = Vaga(this.id, title)

}
