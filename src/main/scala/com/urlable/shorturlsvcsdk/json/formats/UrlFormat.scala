package com.urlable.shorturlsvcsdk.json.formats

import java.net.URL

import com.urlable.shorturlsvcsdk.json.JsonSupport
import spray.json._

object UrlFormat
  extends JsonSupport
    with RootJsonFormat[URL] {

  override def read(json: JsValue): URL = new URL(json.convertTo[String])

  override def write(obj: URL): JsValue =
    JsString(obj.toString)

}
