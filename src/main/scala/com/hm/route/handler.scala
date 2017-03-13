package com.hm.route

import java.io.{FileOutputStream, ObjectOutputStream}

import com.hm.connector.MysqlClient
import spray.json.JsString
import spray.routing.HttpService
import spray.json._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scalaj.http.{Http, HttpRequest}

/**
  * Created by shirin on 2/3/17.
  */

trait handler extends HttpService {
 //var e = 0

  val buf = scala.collection.mutable.ArrayBuffer.empty[Int]

def listall:ArrayBuffer[Int]={
  println("method listall")
 println(buf)
  buf

  }

def add= post {
         entity(as[String]) {

           body => {
             println("inside body")
             val json = body.parseJson.asJsObject
             val t = json.getFields("t").head.asInstanceOf[JsNumber].value.toInt

             println(t)
             // pass broadCast = 1 to ignore broad casting
             val broadCast = if(json.getFields("broadCast").nonEmpty){
               println("inside if")
               json.getFields("broadCast").head.asInstanceOf[JsNumber].value.toInt==0
             }else {
               println("inside else")
           true
             }

             if(broadCast){println("inside update")
               update(t)}
            //a=t
             buf+=t

           }

             complete("elements in the buffer are"+buf)
         }
  }

  def del= post {
    entity(as[String]) {

      body => {
        println("inside body")
        val json = body.parseJson.asJsObject
        val t = json.getFields("t").head.asInstanceOf[JsNumber].value.toInt

        println(t)
        // pass broadCast = 1 to ignore broad casting
        val broadCast = if(json.getFields("broadCast").nonEmpty){
          println("inside if")
          json.getFields("broadCast").head.asInstanceOf[JsNumber].value.toInt==0
        }else {
          println("inside else")
          true
        }

        if(broadCast){println("inside update")
          updateDel(t)}
        //a=t
        buf-=t

      }

        complete("elements in the buffer are"+buf)
    }
  }


  def add1(num : Int)={
  buf+=num
  println("add1"+buf)
}

def update(num:Int) ={
 // e=num
  MysqlClient.updateLiveInstances
MysqlClient.map1.foreach(j=>{
  println("instances "+j._1+","+j._2)
})
  MysqlClient.map1.foreach(i => {
//    val request: HttpRequest = Http("http://" + i._1 + ":" + i._2 + "/badd?e="+e+"")

    val request: HttpRequest = Http("http://" + i._2+ ":" + i._1 + "/add").postData(JsObject(
      "t"->JsNumber(num),
      "broadCast"->JsNumber(1)
    ).toString)
println("host : "+i._2+" port:"+i._1)
    println(request)
    val response = request.asString.body
    println("RESPONSE " + response)
    })
  complete("")
}
  def updateDel(num:Int) ={
    // e=num
    MysqlClient.updateLiveInstances
    MysqlClient.map1.foreach(j=>{
      println("instances "+j._1+","+j._2)
    })
    MysqlClient.map1.foreach(i => {
      //    val request: HttpRequest = Http("http://" + i._1 + ":" + i._2 + "/badd?e="+e+"")

      val request: HttpRequest = Http("http://" + i._2+ ":" + i._1 + "/del").postData(JsObject(
        "t"->JsNumber(num),
        "broadCast"->JsNumber(1)
      ).toString)
      println("host : "+i._2+" port:"+i._1)
      println(request)
      val response = request.asString.body
      println("RESPONSE " + response)
    })
    complete("")
  }
}
