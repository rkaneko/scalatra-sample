import com.rkaneko.app._
import com.rkaneko.controller._
import org.scalatra._
import javax.servlet.ServletContext
import scalikejdbc._

class ScalatraBootstrap extends LifeCycle {


  override def init(context: ServletContext) {

    initDb()

    context.mount(new GoodsController, "/goods/*")

  }

  override def destroy(context: ServletContext) {
    super.destroy(context)
  }

  private def initDb() = {
    // database config
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:inventory_control", "sa", "sa")

    // t_arrival_goods
    DB autoCommit { implicit session =>
      SQL("""
        drop table if exists t_arrival_goods
      """).execute.apply()

      SQL("""
        create table t_arrival_goods (
          id bigint primary key auto_increment,
          goods_id bigint not null,
          arrived_at timestamp not null,
          quantity int not null
        )
      """).execute.apply()
    }
  }

}
