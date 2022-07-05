package uz.yordam.kotlin.games.morskoyboy.controller

import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine
import uz.yordam.kotlin.games.morskoyboy.gameobject.OnePlayer
import uz.yordam.kotlin.games.morskoyboy.gameobject.Room
import java.util.Random

class GameController(
        var vertx: Vertx,
        var templateEngine: FreeMarkerTemplateEngine = FreeMarkerTemplateEngine.create(vertx)
) {

    fun handleHomepage(routingContext: RoutingContext) {
        val response =
            routingContext.response()

        val data = JsonObject()

        templateEngine.render(data, "templates/homePage.ftl") {
            if (it.succeeded()) {
                response
                    .putHeader("content-type", "text/html")
                    .end(it.result())
            } else {
                 handleError404(routingContext)
            }
        }
    }

    fun renderToOnePlayerFtl(routingContext: RoutingContext, data: JsonObject) {
        val response =
                routingContext.response()

        templateEngine.render(data, "templates/onePlayer.ftl") {
            if (it.succeeded()) {
                response
                        .putHeader("content-type", "text/html")
                        .end(it.result())
            } else {
                handleError404(routingContext)
            }
        }
    }

    fun handleStart(routingContext: RoutingContext) {

        val session = routingContext.session()
        session.put("id", Room.getId())
        val onePlayer = OnePlayer(session["id"])
        Room.playerList.add(onePlayer)

        val r = Random()
        do {
            onePlayer.num = (1234 + r.nextInt(8643)).toString()
        } while ('0' in onePlayer.num || onePlayer.num.toSet().size < 4) // converting to set - removing duplicates
        
        println("Random number is " + onePlayer.num)

        val data = JsonObject()
                .put("title", "Bulls and Cows")
                .put("max", onePlayer.getMaxGuesses())
                .put("text", "")
                .put("guesses", onePlayer.guesses)
        renderToOnePlayerFtl(routingContext, data)
  }

    fun handleSendGuess(routingContext: RoutingContext) {

        val session = routingContext.session()
        val onePlayer = Room.playerList.find {
            it.idPlayer == session["id"]
        }!!

        val result = routingContext.request().getParam("guess").toString()
        val msg = onePlayer.logic(result, session["id"])
        val data = JsonObject()
                .put("title", "Bulls and Cows")
                .put("max", onePlayer.getMaxGuesses())
                .put("text", msg)
                .put("guesses", onePlayer.guesses)

        renderToOnePlayerFtl(routingContext, data)
    }

    private fun handleError404(routingContext: RoutingContext) {
        routingContext
                .response()
                .setStatusCode(404)
                .end("no such URL, so Error 404")
    }


}
