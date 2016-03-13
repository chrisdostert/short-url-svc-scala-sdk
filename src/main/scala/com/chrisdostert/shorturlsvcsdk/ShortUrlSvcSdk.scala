package com.chrisdostert.shorturlsvcsdk

import java.net.URL

import com.chrisdostert.shorturlsvcsdk.models.ShortUrlView

import scala.concurrent.Future

class ShortUrlSvcSdk private[shorturlsvcsdk](
  private val compositionRoot: CompositionRoot
) {

  def createShortUrl(target: URL): Future[ShortUrlView] =
    compositionRoot
      .createShortUrlUc
      .execute(target = target)

  def getShortUrlWithId(shortUrlId: String): Future[ShortUrlView] =
    compositionRoot
      .getShortUrlWithIdUc
      .execute(shortUrlId)

}

object ShortUrlSvcSdk {

  def apply(shortUrlSvcSdkConfig: ShortUrlSvcSdkConfig): ShortUrlSvcSdk =
    new ShortUrlSvcSdk(
      new CompositionRoot(
        shortUrlSvcSdkConfig = shortUrlSvcSdkConfig
      )
    )

}


