package com.urlable.shorturlsvcsdk.usecases

import java.io.IOException
import java.net.URL

import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import com.urlable.shorturlsvcsdk.json.JsonSupport
import com.urlable.shorturlsvcsdk.models.ShortUrlView

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

private[shorturlsvcsdk]
class CreateShortUrlUc(
  private val connection: Flow[HttpRequest, HttpResponse, Future[Http.OutgoingConnection]],
  private implicit val materializer: Materializer,
  private implicit val jsonSupport: JsonSupport
) {

  import jsonSupport._

  def execute(target: URL): Future[ShortUrlView] =
    Source
      .single(
        RequestBuilding.Post(
          "/short-urls",
          target
        )
      )
      .via(connection)
      .runWith(Sink.head)(materializer)
      .flatMap { response =>
        response.status match {
          case OK => Unmarshal(response.entity).to[ShortUrlView]
          case _ => Future.failed(new IOException(response.toString))
        }
      }

}
