package manik1.manik1

import monix.execution.Scheduler
import outwatch.dom._
import outwatch.dom.dsl.{span => sp, _}
import monix.reactive.Observable
import cats.effect.IO
import manik1.services.WSCovenant
import monix.execution.Ack.Continue

object HelloWoutWatch {


  import monix.execution.Scheduler.Implicits.global

  def main(args: Array[String]): Unit = {

    val hdl = Handler.create[String]("").unsafeRunSync()

    val makePing = Sink.create[String] { p =>
      WSCovenant.api.ping(p).foreach {
        case Right(value) =>
          hdl.onNext(value)
        case Left(error) =>
          println(error)
      }
      Continue
    }

    def render =
      div(
        button("Websocket", cls:="myButton", onClick.mapTo("ping") --> makePing),
        div( clear.both,
          sp("Message received:", hdl)
        )
      )

    val program = for {
      program <- OutWatch.renderInto("#root", div( render ) )
    } yield program

    program.unsafeRunSync()

  }
}