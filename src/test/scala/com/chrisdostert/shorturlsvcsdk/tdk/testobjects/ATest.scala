package com.chrisdostert.shorturlsvcsdk.tdk.testobjects

/**
  * A fluent test objects API
  */
trait ATest
  extends org.testobjects.ATest {

  lazy val shortUrlView: ATestShortUrlView =
    new ATestShortUrlView {}

}

object ATest extends ATest
