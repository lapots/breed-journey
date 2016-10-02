package com.lapots.game.journey.osm

import java.util.UUID

import com.lapots.game.journey.osm.domain.ObjectState

/**
  * High level wrapper over OSMContext.
  */
object OSMPlatform {

  // any object - but mostly AnyRef objects I presume
  def registerObject(objectInstance: AnyRef, fields: List[String], fieldValues: Map[String, Any]): String = {
    val id = UUID.randomUUID().toString
    val objState = new ObjectState
    // need something better
    objState.registerFields(fields, objectInstance)
    objState.registerState(fieldValues)

    OSMContext.registerObject(id, objState)
    id
  }

  def retrieveObject(id: String): ObjectState = {
    OSMContext.retrieveObject(id)
  }
}
