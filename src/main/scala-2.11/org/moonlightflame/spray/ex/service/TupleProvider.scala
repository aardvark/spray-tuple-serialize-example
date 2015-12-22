package org.moonlightflame.spray.ex.service

/**
  * @author aectann
  */
trait TupleProvider {

  def getStrTuple: (String, String)

  def getStrListTuple: Seq[(String, String)]

}

trait TupleProviderComponent {

  def tupleProvider: TupleProvider

}
