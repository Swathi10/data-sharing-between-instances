package com.hm.app

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.util.Timeout
import com.hm.ServerServiceActor
import com.hm.config.Configuration
import spray.can.Http

/**
  * Created by shirin on 2/3/17.
  */
object Boot extends App with Configuration{
  implicit  val system=ActorSystem("on-spray-can")

  val service=system.actorOf(Props[ServerServiceActor],"test")
  implicit  val timeout=Timeout(5)
  IO(Http) ! Http.Bind(service, serviceHost, servicePort)

}

