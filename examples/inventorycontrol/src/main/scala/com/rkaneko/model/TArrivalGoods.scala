package com.rkaneko.model

import org.joda.time.DateTime
import scalikejdbc._, SQLInterpolation._

case class TArrivalGoods(
  id: Long,
  goodsId: Long,
  arrivedAt: DateTime,
  quantity: Int)

object TArrivalGoods extends SQLSyntaxSupport[TArrivalGoods] {

  override val tableName = "t_arrival_goods"
  override val columnNames = Seq("id", "goods_id", "arrived_at", "quantity")
  val tag = TArrivalGoods.syntax("tag")

  def apply(model: ResultName[TArrivalGoods])(implicit rs: WrappedResultSet): TArrivalGoods = {
    new TArrivalGoods(
      id = rs.long(model.id),
      goodsId = rs.long(model.goodsId),
      arrivedAt = rs.timestamp(model.arrivedAt).toDateTime,
      quantity = rs.int(model.quantity)
    )
  }

  def create(goodsId: Long, arrivedAt: DateTime, quantity: Int)(implicit session: DBSession): TArrivalGoods = {
    val id = withSQL {
      insert.into(TArrivalGoods).namedValues(
        column.goodsId -> goodsId,
        column.arrivedAt -> arrivedAt,
        column.quantity -> quantity
      )
    }.updateAndReturnGeneratedKey.apply()
    TArrivalGoods(id, goodsId, arrivedAt, quantity)
  }

  def find(id: Long)(implicit session: DBSession): Option[TArrivalGoods] = {
    withSQL { select.from(TArrivalGoods as tag).where.eq(tag.id, id) }
      .map { rs => TArrivalGoods(
        id = rs.long(tag.resultName.id),
        goodsId = rs.long(tag.resultName.goodsId),
        arrivedAt = rs.timestamp(tag.resultName.arrivedAt).toDateTime,
        quantity = rs.int(tag.resultName.quantity))
      }.single.apply()
  }

  def findAll()(implicit session: DBSession): List[TArrivalGoods] = {
    withSQL { select.from(TArrivalGoods as tag) }
      .map { implicit rs => TArrivalGoods(tag.resultName) }
      .list.apply()
  }


}
