package com.lapots.game.journey.osm.domain

/**
  * Represent object state.
  */
class ObjectState {
  // object reference
  var objRef : AnyRef = _
  // map that mirrors current object state
  var stateMap : Map[String, Any] = _

  override def toString: String = {
    var initialString = ""
    stateMap foreach {
      case (key, value) =>
        initialString += s"${key} -> ${value}\n"
    }
    initialString
  }

  def registerFields(fields: List[String], objectInstance: AnyRef): Unit = {
    // just register
    objRef = objectInstance
    stateMap = fields.map(field => field -> null)(collection.breakOut)
  }

  def registerDefault(): Unit = {
    Mirror.inMirrorObjectState(this)
  }

  def registerState(fieldValues: Map[String, Any]): Unit = {
    fieldValues foreach {
      case(key, value) =>
        if (value != null) {
          stateMap += (key -> value)
        }
    }
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
