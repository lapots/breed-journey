package com.lapots.game.journey.osm.domain

import com.lapots.game.journey.osm.exception.OSMException

/**
  * Represent object state.
  */
class ObjectState(objRefValue: AnyRef, stateFields: List[String], initialState: Map[String, Any]) {
  // object reference
  var objRef = objRefValue
  // initialize initially
  var stateMap : Map[String, Any] = _

  // initialize state or reflect
  if (initialState.isEmpty) {
    stateMap = stateFields.map(field => field -> null)(collection.breakOut)
    Mirror.inMirrorObjectState(this)
  } else {
    // assert consistency
    if (stateFields.size != initialState.keys.size) {
      throw OSMException("Inconsistent state and fields amount"))
    }
    stateMap = initialState
  }

  def this(objRefValue: AnyRef, stateFields: List[String]) = this(objRefValue, stateFields, Map())
  def this(objRefValue: AnyRef) = this(objRefValue, List(), Map())

  override def toString: String = {
    var initialString = ""
    stateMap foreach {
      case (key, value) =>
        initialString += s"${key} -> ${value}\n"
    }
    initialString
  }

  object Mirror {
    // basically populate object state
    def inMirrorObjectState(state: ObjectState): Unit = {
      val objectInstance = state.objRef
      stateMap.keySet foreach { key =>
        val field = objectInstance.getClass.getDeclaredField(key)
        field setAccessible true
        stateMap += (key -> field.get(objectInstance))
      }
    }

    // set object field values for registered fields
    def outMirrorObjectState(state: ObjectState): Unit = {
      val objectInstance = state.objRef
      stateMap.foreach {
        case (key, value) =>
          val field = objectInstance.getClass.getDeclaredField(key)
          field setAccessible true
          field.set(objectInstance, value)
      }
    }
  }
}
