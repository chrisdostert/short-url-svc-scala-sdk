package com.chrisdostert.shorturlsvcsdk.tdk.testobjects

import java.net.URL

import com.chrisdostert.shorturlsvcsdk.models.ShortUrlView

trait ATestShortUrlView
  extends ATest {


  def withValues(
    id: String = ATest.string.nonEmpty,
    target: URL = new URL("http://dummyurl.com")
  ): ShortUrlView =
    ShortUrlView(
      id,
      target
    )

  val nonNull: ShortUrlView = withValues()

}
