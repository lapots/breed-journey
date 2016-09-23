package com.lapots.game.journey.osm.domain

import com.lapots.game.journey.osm.api.Transformation

/**
  * Special entity that represent transition function.
  *
  * Basically transition represents all the transformations
  * for all the object fields.
  */
class Transition {
  // map that stores transformation rules
  // potentially may use rule engine
  var transformRules : Map[String, Transformation[AnyRef]] = Map()

  def registerTransformation(field : String, transformation : Transformation[AnyRef]): Unit = {
    transformRules += (field -> transformation)
  }
}
