package com.hm.app

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.util.Timeout
import com.hm.ServerServiceActor
import spray.can.Http

/**
  * Created by shirin on 2/3/17.
  */
object Boot extends App {
  implicit  val system=ActorSystem("on-spray-can")

  val service=system.actorOf(Props[ServerServiceActor],"RestApi")
  implicit  val timeout=Timeout(5)
  IO(Http) ! Http.Bind(service, "localhost", 8080)

}
/*def add(temp:ArrayBuffer[Int],p:Int):ArrayBuffer[Int]={
temp+=p
}*/
