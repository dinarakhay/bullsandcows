package uz.yordam.kotlin.games.morskoyboy

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import uz.yordam.kotlin.games.morskoyboy.router.GameRouter

class MainVerticle : AbstractVerticle() {

    override fun start(startPromise: Promise<Void>) {

        val server = vertx.createHttpServer()

        val router = GameRouter().getRouter(vertx)

        server
            .requestHandler(router)
            .listen(80) { http ->
                if (http.succeeded()) {
                    startPromise.complete()
                    println("HTTP server started on port http://localhost:80")
                } else {
                    startPromise.fail(http.cause())
                }
            }


    }
}