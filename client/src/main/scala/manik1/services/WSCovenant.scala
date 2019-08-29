package manik1.services

import java.nio.ByteBuffer


import chameleon.ext.boopickle._
import boopickle.Default._
import java.nio.ByteBuffer

import covenant.ws.WsClient
//import manik1.services.WebSocketMain.port
import mycelium.client.WebsocketClientConfig
import outwatch.dom.dsl.s
import share.covenantAPI.Api

import scala.concurrent.Future

import monix.execution.Scheduler.Implicits.global

import share.covenantAPI.ApiError

object WSCovenant {
  val wsIP = "localhost"
  val wsPort = "8090"
  val config = WebsocketClientConfig(  )
  val client = WsClient[ByteBuffer, Unit, ApiError](s"ws://$wsIP:$wsPort/ws/socketCovenant", config)
  val api = client.sendWithDefault.wire[Api[Future]]
}