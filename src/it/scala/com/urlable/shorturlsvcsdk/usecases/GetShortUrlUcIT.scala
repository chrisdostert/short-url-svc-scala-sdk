package com.urlable.shorturlsvcsdk.usecases

import java.net.URL

import com.urlable.shorturlsvcsdk.models.ShortUrlView
import com.urlable.shorturlsvcsdk.tdk.{BaseFunSpecTest, FutureConfig}
import com.urlable.shorturlsvcsdk.{ShortUrlSvcSdk, ShortUrlSvcSdkConfig}
import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

import scala.concurrent.Await
import scala.language.postfixOps

class GetShortUrlUcIT
  extends BaseFunSpecTest {

  /** fields **/
  private val shortUrlSvcSdkConfig =
    ConfigFactory
      .load()
      .as[ShortUrlSvcSdkConfig]("it")

  lazy val compositionRoot =
    new com.urlable.shorturlsvcsdk.CompositionRoot(shortUrlSvcSdkConfig)

  describe("execute") {

    it("should return the expected ShortUrlView") {

      /** arrange **/
      val objectUnderTest =
        new ShortUrlSvcSdk(
          compositionRoot
        )

      // seed short url so we can retrieve it
      val expectedShortUrlView: ShortUrlView =
        Await.result(
          objectUnderTest
            .createShortUrl(target = new URL("http://dummyurl.com")),
          FutureConfig.defaultTimeout
        )

      /** act **/
      val actualShortUrlView: ShortUrlView =
        Await.result(
          objectUnderTest
            .getShortUrlWithId(shortUrlId = expectedShortUrlView.id),
          FutureConfig.defaultTimeout
        )

      /** assert **/
      assert(actualShortUrlView == expectedShortUrlView)

    }

  }

}
