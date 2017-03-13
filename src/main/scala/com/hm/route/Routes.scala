package com.hm.route

import com.hm.config.Configuration
import com.hm.connector.MysqlClient

import scala.collection.mutable
import scalaj.http.HttpRequest
//import com.sun.deploy.net.HttpRequest

import spray.json.JsArray
import spray.routing.HttpService
import java.io.File

import spray.json._
import java.lang.Exception
import java.net.InetAddress
import java.util
import scala.collection.immutable.ListMap
import com.hm.connector.MysqlClient
import com.maxmind.geoip2.DatabaseReader

import collection.JavaConverters._
import scala.collection.JavaConversions._
import scala.util.control.Breaks._
import spray.http.MediaTypes.`text/html`
import spray.routing.HttpService

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer
import scalaj.http.Http
/**
  * Created by shirin on 2/3/17.
  */
trait Routes extends HttpService with handler with Configuration{
/*var i = true
  println("1.ADD 2.DELETE 3.TEST")
  while(i) {
  println("enter the option")
  val temp:Int=scala.io.StdIn.readInt()

  temp match {
    case 1 => add
   case 2 => deleteElement
    case 3 => test
    case 4 => i=false
    case _ => println("enter valid input")
  }
}*/
  val route =
    path("add"){
      add

  }~path("")
  {
    val rs=MysqlClient.executeQuery("insert into liveconn(servicehost,port) values('"+serviceHost+"',"+servicePort+")")
    complete("instance added")
  }~path("list") {

      MysqlClient.getLiveInstances
     println(MysqlClient.map)
//      var request:HttpRequest=Http("")
      MysqlClient.map.foreach(i=>{
       val request:HttpRequest = Http("http://"+i._1+":"+i._2+"/listall")
        println(request)
        val response = request.asString.body
        println(response)
      //  response=response.toJson
      val tmp = response.parseJson.asInstanceOf[JsArray]
        complete(tmp.prettyPrint)
      })

      //tmp.elements.foreach(i=>i.asInstanceOf[JsNumber].))
      complete("")

    }~path("listall") {
    println("inside listall")
    var array = scala.collection.mutable.ArrayBuffer.empty[Int]
    array=listall
println("......"+array)
    complete(""+array)


  }

      // rs.map(i=>print(i._1+"  "+i._2))
      /* val request: HttpRequest = Http("http://localhost:8080/add?e=56")
       val response = request.asString.body
       val tmp = response.parseJson.asInstanceOf[JsArray]
         //tmp.elements.foreach(i=>i.asInstanceOf[JsNumber].))
         complete(tmp.prettyPrint)
      func*/

      // complete(JsArray(a.map(i=>JsNumber(i)).toVector).prettyPrint)


      // ~path("delete"){
//     deleteElement
//
//    }~path("test"){
//      test
//
//    }~path("list") {
//       list
//
////    }~ path("") {
//
//        get {
//          respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
//            complete {
//              <html>
//                <body>
//                  <h1> welcome :)</h1>
//                </body>
//              </html>
//            }
//          }
//        }
//      }
//    }
}
