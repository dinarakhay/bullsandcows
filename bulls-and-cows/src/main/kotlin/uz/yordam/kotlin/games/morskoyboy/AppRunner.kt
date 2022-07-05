package uz.yordam.kotlin.games.morskoyboy

import io.vertx.core.Vertx

fun main() {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(MainVerticle())
}
//.btn {
//    background-color: Lime;
//    color: tomato;
//    font-weight: bold;

//}