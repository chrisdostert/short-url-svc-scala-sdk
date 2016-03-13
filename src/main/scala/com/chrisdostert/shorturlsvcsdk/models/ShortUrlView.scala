package com.chrisdostert.shorturlsvcsdk.models

import java.net.URL

private[shorturlsvcsdk]
case class ShortUrlView(
  id: String,
  target: URL
)
