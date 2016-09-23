package com.lapots.game.journey.osm.domain

/**
  * Special entity that represent object state in SM.
  */
class State {
  val id : String = _
  // map (retrieved via reflection I presume) of object fields and its values
  val objectParameters = Map[String, AnyRef]
}
