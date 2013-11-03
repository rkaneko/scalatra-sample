package com.rkaneko.util

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

trait JsonSupport extends JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  val contentTypeJson = formats("json")
}
