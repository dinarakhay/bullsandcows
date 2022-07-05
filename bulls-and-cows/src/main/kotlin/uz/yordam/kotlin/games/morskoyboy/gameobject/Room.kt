package uz.yordam.kotlin.games.morskoyboy.gameobject

object Room {
    val playerList = mutableListOf<OnePlayer>()
    var idKeep = 0

    fun getId() = ++idKeep

}