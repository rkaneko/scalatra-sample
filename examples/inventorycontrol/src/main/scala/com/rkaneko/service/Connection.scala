package com.rkaneko.service

import scalikejdbc.ConnectionPool

trait Connection {
  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:inventory_control", "sa", "sa")
}
