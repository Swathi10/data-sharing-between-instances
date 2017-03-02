package com.hm

import java.util.concurrent.Executors

import akka.actor.ActorSystem

import scala.concurrent.ExecutionContext

/**
  * Created by shirin on 2/3/17.
  */
object ServerActorSystem {


  implicit val system:ActorSystem = ActorSystem()
  implicit val ec:ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))

}