package org.moonlightflame.spray.ex.controller

import org.moonlightflame.spray.ex.service.TupleProviderComponent
import spray.routing.Route

/**
  * @author aectann
  */
trait TupleController {

  this: TupleController
    with TupleProviderComponent =>

  import spray.httpx.SprayJsonSupport._
  import spray.json.DefaultJsonProtocol._
  import spray.routing.Directives._

  def tupleRoute: Route =
      path("getListAsObject") {
        import org.moonlightflame.spray.ex.converter.SingleObjectJsonProtocol._

        complete { listOfTupleFormatAsSingleObject[String].write(tupleProvider.getStrListTuple) }
      } ~
      path("getStr") {
        import org.moonlightflame.spray.ex.converter.JsonProtocol._

        complete { objectedTuple2Format[String].write(tupleProvider.getStrTuple) }
      } ~
      path("getListAsListOfObjects") {
        import org.moonlightflame.spray.ex.converter.JsonProtocol._

        complete { tupleProvider.getStrListTuple }
      }


}

trait TupleControllerComponent {

  def service: TupleController

}

