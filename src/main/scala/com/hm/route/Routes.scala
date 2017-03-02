package com.hm.route

import spray.http.MediaTypes.`text/html`
import spray.routing.HttpService

/**
  * Created by shirin on 2/3/17.
  */
trait Routes extends HttpService with handler{
var i = true
  println("1.ADD 2.DELETE 3.TEST")
  while(i) {
  println("enter the number")
  val temp:Int=scala.io.StdIn.readInt()

  temp match {
    case 1 => add
    case 2 => deleteElement
    case 3 => test
    case 4 => i=false
    case _ => println("enter valid input")
  }
}
  val route =
    path("") {

      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1> welcome :)</h1>
              </body>
            </html>
          }
        }
      }
    }
}
