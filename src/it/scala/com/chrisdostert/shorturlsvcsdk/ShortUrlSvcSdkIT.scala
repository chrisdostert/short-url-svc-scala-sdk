package com.chrisdostert.shorturlsvcsdk

import com.chrisdostert.shorturlsvcsdk.tdk.BaseFunSpecTest
import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

class ShortUrlSvcSdkIT extends BaseFunSpecTest {

  /** fields **/
  private val shortUrlSvcSdkConfig =
    ConfigFactory
      .load()
      .as[ShortUrlSvcSdkConfig]("it")

  describe("apply()") {
    it("should return instance") {

      /** arrange/act **/
      val actualResult = ShortUrlSvcSdk(shortUrlSvcSdkConfig)

      /** assert **/
      //noinspection ScalaStyle
      assert(null != actualResult)

    }
  }

}
