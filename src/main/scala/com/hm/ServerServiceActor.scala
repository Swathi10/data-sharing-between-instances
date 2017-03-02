package com.hm

import akka.actor.Actor
import com.hm.route.Routes

import scala.concurrent.ExecutionContext

/**
  * Created by shirin on 2/3/17.
  */
class ServerServiceActor  extends Actor with Routes{

  //Akka Actor Context is set as the actorRefFactory
  def actorRefFactory = context

  //rootRoute is defined in "com.hm.routes.Routes"
  def receive = runRoute(route)

  implicit def dispatcher: ExecutionContext = ServerActorSystem.ec
}
