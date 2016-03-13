package com.chrisdostert.shorturlsvcsdk.usecases

import java.net.URL

import com.chrisdostert.shorturlsvcsdk.models.ShortUrlView
import com.chrisdostert.shorturlsvcsdk.tdk.{BaseFunSpecTest, FutureConfig}
import com.chrisdostert.shorturlsvcsdk.{CompositionRoot, ShortUrlSvcSdk, ShortUrlSvcSdkConfig}
import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

import scala.concurrent.Await
import scala.language.postfixOps

class CreateShortUrlUcIT
  extends BaseFunSpecTest {

  /** fields **/
  private val shortUrlSvcSdkConfig =
    ConfigFactory
      .load()
      .as[ShortUrlSvcSdkConfig]("it")

  lazy val compositionRoot =
    new CompositionRoot(shortUrlSvcSdkConfig)

  describe("execute") {

    it("should return a ShortUrlView") {

      /** arrange **/
      val objectUnderTest =
        new ShortUrlSvcSdk(
          compositionRoot
        )

      val providedTarget =
        new URL("http://dummyurl.com")

      /** act **/

      val returnedShortUrlView: ShortUrlView =
        Await.result(
          objectUnderTest
            .createShortUrl(target = providedTarget),
          FutureConfig.defaultTimeout
        )

      /** assert **/
      //noinspection ScalaStyle
      assert(null != returnedShortUrlView)

    }

  }

}
