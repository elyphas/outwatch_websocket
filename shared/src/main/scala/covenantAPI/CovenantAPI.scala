package share.covenantAPI

case class ApiError(msg: String)

trait Api[F[_]] {

  def ping(p: String): F[Either[String, String]]

}