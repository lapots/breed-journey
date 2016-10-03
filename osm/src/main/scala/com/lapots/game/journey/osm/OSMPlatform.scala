package com.lapots.game.journey.osm

import java.util.UUID

import com.lapots.game.journey.osm.domain.ObjectState

/**
  * High level wrapper over OSMContext.
  */
object OSMPlatform {

  // any object - but mostly AnyRef objects I presume
  def registerObject(objectInstance: AnyRef, fields: List[String]): String = {
    val id = UUID.randomUUID().toString
    val objState = new ObjectState
    
    objState.registerFields(fields, objectInstance)

    OSMContext.registerObject(id, objState)
    id
  }

  def registerInitialState(id: String): Unit = {
    OSMContext.retrieveObject(id).registerDefault()
  }

  def registerState(id: String, state: Map[String, Any]): Unit = {
    OSMContext.retrieveObject(id).registerState(state)
  }

  def retrieveObject(id: String): ObjectState = {
    OSMContext.retrieveObject(id)
  }
}
