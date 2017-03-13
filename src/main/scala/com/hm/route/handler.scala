package com.hm.route

import java.io.{FileOutputStream, ObjectOutputStream}

import com.hm.connector.MysqlClient
import spray.json.JsString
import spray.routing.HttpService
import spray.json._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by shirin on 2/3/17.
  */

trait handler extends HttpService {
  val buf = scala.collection.mutable.ArrayBuffer.empty[Int]
//  buf+=5
//  buf+=10
//  buf+=22
//  buf+=14
def listall:ArrayBuffer[Int]={
 buf
  }
def add={
  post {
         entity(as[String]) {
           body => {
             val json = body.parseJson.asJsObject
             val t = json.getFields("t").head.asInstanceOf[JsString].value.toInt
            buf+=t
           }
             complete("elements in the buffer are"+buf)
         }
  }
}


}
//  //val res: ArrayBuffer[Int] = ArrayBuffer(1,2,3,4,78,43,80)
// /* println("enter the size")
//  val b :Int =scala.io.StdIn.readInt()
//  var a=0
//  var res=0
//  for(a<-0 to b){
//    println("Enter the number")
//
//    res=scala.io.StdIn.readInt()
//    insertData(res)
//  }*/
// /* ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test.ser"));
//  out.writeObject(new Int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
//  out.flush();
//  out.close();*/
//  //val mutable.TreeSet<Int> res=new mutable.TreeSet<Int>();
////var t=0
//  def add= {
//   /*println("Enter the number to be added")
//  t=scala.io.StdIn.readInt()*/
//   post {
//     entity(as[String]) {
//       body => {
//         val json = body.parseJson.asJsObject
//         val t = json.getFields("t").head.asInstanceOf[JsString].value
//         val rs = MysqlClient.executeQuery("insert into Array(value) values (" + t + ")")
//         //insertData1(t)
//         val res = MysqlClient.getResultSet("select *from Array")
//         println("The new array is ")
//         while (res.next()) {
//            print(res.getInt("value")+" ")
//          // complete("array" + res.getInt("value"))
//         }
//       }
//         complete(" ")
//         //println()
//
//
//     }
//   }
// }
// def deleteElement= {
//   /*println("Enter the number to be deleted")
//     t  =scala.io.StdIn.readInt()
//   deleteNo(t)*/
//
//   post {
//     entity(as[String]) {
//       body => {
//         val json = body.parseJson.asJsObject
//         val t = json.getFields("t").head.asInstanceOf[JsString].value
//         val res = MysqlClient.executeQuery("delete from Array where value= " + t + "")
//
//         val rs = MysqlClient.getResultSet("select *from Array")
//         println("The new array is ")
//         while (rs.next()) {
//           print(rs.getInt("value")+" ")
//           //complete("array" + rs.getInt("value"))
//         }
//         //println()
//
//       }
//         complete(" ")
//     }
//   }
// }
//
//  def test= {
//    /* println("Enter the number ")
//     t =scala.io.StdIn.readInt()*/
//
//    post {
//      entity(as[String]) {
//        body => {
//          val json = body.parseJson.asJsObject
//          val t = json.getFields("t").head.asInstanceOf[JsString].value
//          val rs = MysqlClient.getResultSet("select max(value) from Array where value<" + t)
//          val res = MysqlClient.getResultSet("select min(value) from Array where value>" + t)
//          while (rs.next() && res.next()) {
//
//            println("min = " + rs.getInt(1) + " And max = " + res.getInt(1))
//            //complete("min= "+rs.getInt("1")+" And max= "+res.getInt(1))
//          }
//          complete(" ")
//        }
//      }
//    }
//  }
//   /* val rs =MysqlClient.getResultSet("select *from Array")
//    val res: ArrayBuffer[Int] = ArrayBuffer()
//    //var i=0
//
//    while(rs.next()) {
//     res+=rs.getInt(1)
//
//
//    }
//    println(res)
//    val k=res.filter(_>t)
//    val h=res.filter(_<t)
//   val max= res.filter(_>t).sorted.head
//    val min=res.filter(_<t).sorted.last
//
//println("Element greater than " +t+ "is "+max)
//    println("Element lesser than "+t+ " is "+min)
//  }*/
// /* def insertData(values:ArrayBuffer):Boolean={
//
//
//    MysqlClient.statement.setInt(1,values)
//
//   MysqlClient.statement.setString(2,path)
//
//    MysqlClient.statement.addBatch()
//
//
//    true
//  }
//*/
// /* def insertData1(value: Int) = {
//
//    val rs = MysqlClient.executeQuery("insert into Array(value) values (" + t +")")
//
//  }
//  def deleteNo(value: Int) = {
//
//    val rs = MysqlClient.executeQuery("delete from Array where value= "+t+"")
//
//  }
//*/
//
//}
