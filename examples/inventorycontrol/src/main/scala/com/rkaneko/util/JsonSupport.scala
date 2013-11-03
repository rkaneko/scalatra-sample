package com.rkaneko.util

import org.json4s.{DefaultFormats, Formats}
import org.json4s.ext.JodaTimeSerializers
import org.scalatra.json._

trait JsonSupport extends JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats ++ JodaTimeSerializers.all

  val contentTypeJson = formats("json")
}
