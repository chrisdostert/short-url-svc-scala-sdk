package com.chrisdostert.shorturlsvcsdk.tdk

import scala.concurrent.duration._

trait FutureConfig {

  final val defaultTimeout = 120 seconds

}

object FutureConfig extends FutureConfig
