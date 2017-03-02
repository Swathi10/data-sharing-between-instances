package com.hm.route

import spray.routing.HttpService

import scala.collection.mutable.ArrayBuffer

/**
  * Created by shirin on 2/3/17.
  */
trait handler extends HttpService{
  val res: ArrayBuffer[Int] = ArrayBuffer(1,2,3,4,78,43,80)

def add={
  println("Enter the number to be added")
  val t :Int =scala.io.StdIn.readInt()
  res += t
  println("The new array "+res)
}
  def deleteElement={
    println("Enter the number to be deleted")
    val t :Int =scala.io.StdIn.readInt()
    res -= t
    println("The new "+res)
  }

  def test={
    println("Enter the number ")
    val t :Int =scala.io.StdIn.readInt()
    /*val k=res.filter(_>t)
    val h=res.filter(_<t)*/
   val max= res.filter(_>t).sorted.head
    val min=res.filter(_<t).sorted.last

println("Element greater than "+t+ "is "+max)
    println("Element lesser than "+t+ "is "+min)
  }

}
