package com.lapots.game.journey.osm.api

/**
  * Trait that represent transformation over a field.
  */
trait ITransformation {
  def transform(input: Any, misc: Map[String, String]) : Any
}
