package com.lapots.game.journey.osm.domain

import com.lapots.game.journey.osm.OSMContext

/**
  * Special entity that represent object state in SM.
  *
  * Due to architecture I think I can get rid of that object though.
  */
class ObjectState {
  var id : String = _
  // map (retrieved via reflection I presume) of object fields and its values
  // should mirror real object for id
  var objectParameters : Map[String, AnyRef] = Map()

  override def toString: String = {
    "Object id: $id ; Object status: $objectParameters"
  }

  object Action {
    def applyState(state: ObjectState, objId: String): Unit = {
      val storedObject = OSMContext.retrieveObject(objId).getContent()
      storedObject.getClass.getFields.foreach(field =>
        if (state.objectParameters(field.getName) != null) {
          field.set(storedObject, state.objectParameters(field.getName))
        }
      )
    }
  }
}
