package covenantBackend

import monix.execution.Scheduler.Implicits.global
import covenant.core.api._
import share.covenantAPI._

import scala.concurrent.Future

object Covenant {

  type Event = String
  type State = String

  object Dsl extends ApiDsl[Event, ApiError, State] {
    override def applyEventsToState(state: State, events: Seq[Event]): State = state + "" + events.mkString(",")
    override def unhandledException(t: Throwable): ApiError = ApiError(t.getMessage)
  }

  //object ApiImpl extends Api[Future] {
  object ApiImpl extends Api[Dsl.ApiFunction] {
    import Dsl._

    var seqChunks: Seq[Array[Byte]] = Seq.empty

    implicit val ec = scala.concurrent.ExecutionContext.Implicits.global

    def ping(p: String): ApiFunction[Either[String, String]] = Action { state =>
      Future.successful(Right("pong"))
    }

  }
}