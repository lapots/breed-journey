package com.lapots.game.journey.osm.api

/**
  * Trait that represent transformation over during state change.
  *
  * Currently I expect to have even duplicating transition functions for
  * different arguments. Maybe when I decide to implement rule engine
  * I'll go for some better approach.
  */
trait ITransformation[T] {
  /**
    * Performs transformation action over an object.
    *
    * @param input object parameter to transform
    * @param misc any additional options
    * @return transformed parameter
    */
  def transform(input: T, misc: Map[String, String]) : T
}
