package com.rkaneko.service

import com.rkaneko.model.TArrivalGoods
import org.joda.time.DateTime
import scalikejdbc._

trait GoodsService extends ServiceBase {

  def arrive(goodsId: Long, arrivedAt: DateTime, quantity: Int): TArrivalGoods = {
    DB autoCommit { implicit session =>
      TArrivalGoods.create(goodsId, arrivedAt, quantity)
    }
  }

  def findAllArrivalGoods(): List[TArrivalGoods] = {
    DB autoCommit { implicit session =>
      TArrivalGoods.findAll()
    }
  }
}

object GoodsService extends GoodsService
