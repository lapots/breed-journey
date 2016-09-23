package com.lapots.game.journey.osm.api

abstract class AbstractStatefulObject[T <: scala.AnyRef] extends IStateful {
  var obj : T
}
