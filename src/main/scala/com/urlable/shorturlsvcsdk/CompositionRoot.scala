package com.urlable.shorturlsvcsdk

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.scaladsl.Flow
import akka.stream.{ActorMaterializer, Materializer}
import com.urlable.shorturlsvcsdk.json.JsonSupport
import com.urlable.shorturlsvcsdk.usecases._

import scala.concurrent.Future

private[shorturlsvcsdk]
class CompositionRoot(
  shortUrlSvcSdkConfig: ShortUrlSvcSdkConfig
) {

  private implicit val actorSystem = ActorSystem("short-url-svc-sdk")

  private implicit val actorMaterializer: Materializer = ActorMaterializer()

  // factories
  private lazy val supplierPlatformConnectionFlow:
  Flow[HttpRequest, HttpResponse, Future[Http.OutgoingConnection]] =
    Http().outgoingConnection(
      shortUrlSvcSdkConfig.host,
      shortUrlSvcSdkConfig.port
    )

  private lazy val jsonSupport = JsonSupport

  // use cases
  lazy val createShortUrlUc =
    new CreateShortUrlUc(
      connection = supplierPlatformConnectionFlow,
      materializer = actorMaterializer,
      jsonSupport = jsonSupport
    )

  val getShortUrlWithIdUc =
    new GetShortUrlWithIdUc(
      connection = supplierPlatformConnectionFlow,
      materializer = actorMaterializer,
      jsonSupport = jsonSupport
    )

}
