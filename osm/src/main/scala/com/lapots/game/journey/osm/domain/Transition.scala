package com.lapots.game.journey.osm.domain

import com.lapots.game.journey.osm.api.ITransformation
import com.lapots.game.journey.osm.exception.OSMException

/**
  * Represent transition function which a number of transformations.
  */
class Transition {
  // map of transformation over the object fields.
  var transformations : Map[String, ITransformation] = Map()

  def registerTransformation(field: String, transformation: ITransformation): Unit = {
    if (transformations.exists(_._1 == field)) { throw OSMException("Transformation for that field is already registered!") }
    transformations += (field -> transformation)
  }

  object Apply {
    def applyTransformations(transition: Transition, state: ObjectState): ObjectState = {
      transition.transformations foreach {
        case (key, value) =>
          state.stateMap += (key ->  value.transform(state.stateMap(key), Map()))
      }
      state.Mirror.outMirrorObjectState(state)
      return state
    }
  }
}
