package uz.yordam.kotlin.games.morskoyboy.router

import io.vertx.core.Vertx
import io.vertx.core.http.CookieSameSite
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.SessionHandler
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.ext.web.sstore.LocalSessionStore
import uz.yordam.kotlin.games.morskoyboy.controller.DefaultController
import uz.yordam.kotlin.games.morskoyboy.controller.GameController

class GameRouter {

    fun getRouter(vertx: Vertx): Router {
        val gameController = GameController(vertx)
        val defaultController = DefaultController()
        val sessionContainer = LocalSessionStore.create(vertx)
        val sessionHandler = SessionHandler.create(sessionContainer)
        sessionHandler.setCookieSameSite(CookieSameSite.STRICT)

        val router = Router.router(vertx)

        router
                .route()
                .handler(sessionHandler)

        router
                .get("/")
                .handler(gameController::handleHomepage)

        router
                .get("/start")
                .handler(gameController::handleStart)

        router
                .get("/send-guess")
                .handler(gameController::handleSendGuess)

        router
                .get("/this-url-not-working")
                .handler(defaultController::handleError404)

        router
                .get("/error404")
                .handler(defaultController::handleError404)

        router
            .route("/assets/*")
            .handler (
                StaticHandler.create()
                    .setWebRoot("webroot/assets/")
                    .setCachingEnabled(false)
            )

        router.route().handler(defaultController::handleError404)

        return router
    }
}