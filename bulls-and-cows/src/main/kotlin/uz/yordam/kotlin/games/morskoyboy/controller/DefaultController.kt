package uz.yordam.kotlin.games.morskoyboy.controller

import io.vertx.ext.web.RoutingContext

class DefaultController {

    fun handleError404(routingContext: RoutingContext) {
        routingContext
                .response()
                .setStatusCode(404)
                .end("no such URL, so Error 404")
    }
}