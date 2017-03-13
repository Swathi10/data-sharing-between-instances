package com.hm.connector

import java.sql.{Connection, DriverManager, ResultSet}
import java.util

import com.hm.config.Configuration

import collection.JavaConversions._
import scala.collection.mutable
import spray.routing.HttpService
/**
  * Created by shirin on 17/2/17.
  */
object MysqlClient extends Configuration{
  val map:mutable.HashMap[String,Int]={ new mutable.HashMap[String, Int]}
  val map1:mutable.HashMap[Int,String]={ new mutable.HashMap[Int,String]}
  private val dbc = "jdbc:mysql://" + "127.0.0.1" + ":" + 3306 + "/" + "mysql" + "?user=" + "root" + "&password=" + "root"
  classOf[com.mysql.jdbc.Driver]
  private var conn: Connection = DriverManager.getConnection(dbc)

  def getConnection: Connection = {
    if (conn.isClosed) {
      conn = DriverManager.getConnection(dbc)
    }
    conn
  }
  val autoIncValuesForTable: Map[String, Array[String]] = Map(
    "id" -> Array("id")

  )

  def closeConnection() = conn.close()

  def executeQuery(query: String): Boolean = {
    val statement = getConnection.createStatement()
    try
      statement.execute(query)
    finally statement.close()
  }

  def getResultSet(query: String): ResultSet={
    val statement=getConnection.createStatement()
    statement.executeQuery(query)
  }
  def getLiveInstances={

    var rs=getResultSet("select * from liveconn where servicehost <>'"+serviceHost+"' OR port<>"+servicePort+" order by port limit 1" )

    if (rs.next()){
      println(rs.getString("servicehost"),rs.getInt("port"))
      map.put(rs.getString(2),rs.getInt(3))
      println(map)

    }


  }

  def updateLiveInstances={

    var rs=getResultSet("select * from liveconn where servicehost <>'"+serviceHost+"' OR port<>"+servicePort+" order by port" )

    while(rs.next()){
      println(rs.getString("servicehost"),rs.getInt("port"))
      map1.put(rs.getInt(3),rs.getString(2))
      println("map1"+map1)

    }


  }

}
