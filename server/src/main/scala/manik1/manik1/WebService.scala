package manik1.manik1

import java.nio.ByteBuffer

import scala.util.Success
import akka.http.scaladsl.server.Directives
import manik1.manik1.twirl.Implicits._
import share.covenantAPI.{Api, ApiError}
import sloth.{Router, ServerFailure}
import monix.execution.Scheduler.Implicits.global
import scala.util.Failure

/*********Covenant   ***/
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, OverflowStrategy}
import covenant.ws.AkkaWsRoute
import covenant.ws.api.WsApiConfigurationWithDefaults
import covenantBackend.Covenant.{ApiImpl, Dsl, Event, State}
/**********************/

/** for Mycelium**/
import mycelium.core.AkkaMessageBuilder.AkkaMessageBuilderByteBuffer
import boopickle.Default._
import chameleon.ext.boopickle._
import mycelium.server.WebsocketServerConfig
/**  */

class WebService() extends Directives {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val api = new WsApiConfigurationWithDefaults[Event, ApiError, State]{
    override def dsl = Dsl
    override def initialState: State = ""
    override def isStateValid(state: State): Boolean = true
    override def serverFailure(error: ServerFailure): ApiError = ApiError(error.toString)
  }

  val router = Router[ByteBuffer, Dsl.ApiFunction].route[Api[Dsl.ApiFunction]](ApiImpl)
  val config = WebsocketServerConfig(bufferSize = 5, overflowStrategy = OverflowStrategy.fail)
  val routeWS = AkkaWsRoute.fromApiRouter(router, config, api)

  val route = {
    pathSingleSlash {
      get {
        complete(manik1.manik1.html.index.render(""))
      }
    } ~ pathPrefix("assets" / Remaining) { file =>
        // optionally compresses the response with Gzip or Deflate  // if the client accepts compressed responses
        encodeResponse {
          getFromResource("public/" + file)
        }
      } ~ pathPrefix("ws") {
        path("socketCovenant") {
          routeWS
        }
      }
    }
}