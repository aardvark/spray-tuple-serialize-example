package org.moonlightflame.spray.ex

import akka.actor.{Actor, ActorSystem, Props}
import akka.io.IO
import org.moonlightflame.spray.ex.controller.{TupleController, TupleControllerComponent}
import org.moonlightflame.spray.ex.service.impl.StaticTupleProvider
import org.moonlightflame.spray.ex.service.{TupleProvider, TupleProviderComponent}
import spray.can.Http
import spray.routing.HttpService

/**
  * @author aectann
  */
object Server extends App with ContextWiring {

  implicit val actorSystem = ActorSystem("spray-routing")

  val router = actorSystem.actorOf(Props(new Controller), "router")

  IO(Http) ! Http.Bind(router, interface = "localhost", port = 5050)

}

trait ContextWiring {

  trait TupleProviderComponentImpl extends TupleProviderComponent {
    override def tupleProvider: TupleProvider = new StaticTupleProvider
  }

  trait TupleControllerComponentImpl extends TupleControllerComponent {
    override def service: TupleController = new TupleController with TupleProviderComponentImpl
  }

  class Controller extends Actor
    with HttpService
    with TupleControllerComponentImpl {

    override def receive = runRoute(service.tupleRoute)

    override implicit def actorRefFactory = context
  }


}
