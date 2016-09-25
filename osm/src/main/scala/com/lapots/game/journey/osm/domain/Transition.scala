package com.lapots.game.journey.osm.domain

import com.lapots.game.journey.osm.OSMContext
import com.lapots.game.journey.osm.api.{AbstractStatefulObject, ITransformation}

/**
  * Special entity that represent transition function.
  *
  * Basically transition represents all the transformations
  * for all the object fields.
  *
  * One transition represent one transition function with one
  * transformation for corresponding fields.
  */
class Transition {
  // map that stores transformation rules
  // potentially may use rule engine
  var transformRules : Map[String, ITransformation[AnyRef]] = Map()

  def registerTransformation[T](field : String, transformation : ITransformation[T]): Unit = {
    transformRules += (field -> transformation)
  }

  object Action {
    def applyTransition(transition: Transition, objId: String): Unit = {
      val storedObject = OSMContext.retrieveObject(objId).getContent()
      storedObject.getClass.getFields.foreach(field =>
        if (transition.transformRules(field.getName) != null) {
          // investigate usage of in-class [transformRules]
          field.set(storedObject, transition.transformRules(field.getName))
        }
      )
    }
  }
}
