package org.moonlightflame.spray.ex.converter

import spray.json._

/**
  * @author aectann
  */
object JsonProtocol {

  private type JF[T] = JsonFormat[T]

  implicit def objectedTuple2Format[B :JF] = new RootJsonFormat[(String, B)] {

    def write(t: (String, B)) = JsObject((t._1, t._2.toJson))

    def read(value: JsValue) = value match {
      case v: JsObject => (v.fields.head._1, v.fields.head._2.convertTo[B])
      case x => deserializationError("Expected Tuple2 as JsObject, but got " + x)
    }
  }

  implicit def listOfTupleFormatAsListOfObjects[B :JF] = new RootJsonFormat[Seq[(String, B)]] {

    val singleFormatter = objectedTuple2Format

    def write(t: Seq[(String, B)]) = JsArray(t.map(singleFormatter.write).toVector)

    def read(value: JsValue) = value match {
      case v: JsArray => v.elements.map(singleFormatter.read)
      case x => deserializationError("Expected Tuple2 Seq as JsArray, but got " + x)
    }
  }

}
