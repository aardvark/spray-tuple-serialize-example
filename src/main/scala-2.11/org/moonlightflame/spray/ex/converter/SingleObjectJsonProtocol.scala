package org.moonlightflame.spray.ex.converter

import spray.json._

/**
  * @author aectann
  */
object SingleObjectJsonProtocol {

  private type JF[T] = JsonFormat[T]

  implicit def listOfTupleFormatAsSingleObject[B :JF] = new RootJsonFormat[Seq[(String, B)]] {

    def write(t: Seq[(String, B)]) = JsObject(t.map(tup => (tup._1, tup._2.toJson)).toMap)

    def read(value: JsValue) = value match {
      case v: JsObject => v.fields.mapValues(_.convertTo[B]).toSeq
      case x => deserializationError("Expected Tuple2 Seq as JsObject, but got " + x)
    }
  }
}
