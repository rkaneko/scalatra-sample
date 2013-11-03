package com.rkaneko.controller

import com.rkaneko.util.JsonSupport
import com.rkaneko.model.TArrivalGoods
import com.rkaneko.service.GoodsService
import org.joda.time.DateTime

class GoodsController extends ControllerBase
  with JsonSupport with GoodsService {


  get("/foo") {
    val tag = arrive(1, new DateTime(), 1)
    <p>Inserted {tag.goodsId} {tag.arrivedAt.toString()}</p>
  }

  get("/arrive") {
    (params.get("goodsId"), params.get("quantity")) match {
      case (Some(goodsId), Some(quantity)) =>
        val tag = arrive(goodsId.toLong, new DateTime(), quantity.toInt)
//         <p>Arrived {tag.goodsId}, at {tag.arrivedAt.toString()}</p>
        contentType = contentTypeJson
        tag
      case _ => halt(400) // Bad request
    }
  }

  get("/list") {
    val list = findAllArrivalGoods()
    list.foreach { goods => println(s"""ID:${goods.id}, arrivedAt:${goods.arrivedAt.toString()}""") }
    contentType = "text/html"
    ssp("/goods/list", "goodsList" -> list)
//     templateEngine.layout("/WEB-INF/views/goods/list.ssp")
  }

}
