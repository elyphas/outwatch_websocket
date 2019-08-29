package manik1.services

import chameleon.ext.boopickle._
import boopickle.Default._
import java.nio.ByteBuffer

import mycelium.client.{IncidentHandler, JsWebsocketConnection, WebsocketClient}

import mycelium.client.WebsocketClientConfig
import monix.execution.Scheduler.Implicits.global

object WSMyCelium {

  type Payload = ByteBuffer
  type Event = String
  type Failure = String

  val ipWSCatalogs = "localhost"
  val portCtalogs = "8080"

  val ws = WebsocketClient.withPayload[ByteBuffer, ByteBuffer, Event, Failure](
              new JsWebsocketConnection, WebsocketClientConfig(), new IncidentHandler[Event])

  ws.run( s"ws://$ipWSCatalogs:$portCtalogs/ws/socketMycelium")




}