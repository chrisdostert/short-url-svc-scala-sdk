package com.urlable.shorturlsvcsdk.usecases

import java.io.IOException

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
class GetShortUrlWithIdUc(
  connection: Flow[HttpRequest, HttpResponse, Future[Http.OutgoingConnection]],
  implicit val materializer: Materializer,
  implicit val jsonSupport: JsonSupport
) {

  import jsonSupport._

  def execute(shortUrlId: String): Future[ShortUrlView] =
    Source
      .single(
        RequestBuilding.Get(
          s"/short-urls/$shortUrlId"
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
