package org.moonlightflame.spray.ex.service.impl

import org.moonlightflame.spray.ex.service.TupleProvider

/**
  * @author aectann
  */
class StaticTupleProvider extends TupleProvider {

  override def getStrTuple: (String, String) = ("greeting", "Hello, man!")

  override def getStrListTuple: Seq[(String, String)] =
    Seq(("USA", "MIT"), ("Europe", "ETH Zurich"), ("Asia", "National University of Singapore"))

}
