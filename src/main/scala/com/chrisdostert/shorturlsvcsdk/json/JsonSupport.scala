package com.chrisdostert.shorturlsvcsdk.json

import java.net.URL

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.chrisdostert.shorturlsvcsdk.json.formats.UrlFormat
import com.chrisdostert.shorturlsvcsdk.models._
import spray.json._

private[shorturlsvcsdk]
trait JsonSupport
  extends SprayJsonSupport
    with DefaultJsonProtocol {


  implicit val urlFormat: RootJsonFormat[URL] = UrlFormat

  implicit val shortUrlViewFormat: RootJsonFormat[ShortUrlView] =
    jsonFormat2(ShortUrlView)

}

object JsonSupport extends JsonSupport
