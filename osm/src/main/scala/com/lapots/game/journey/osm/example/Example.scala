package com.lapots.game.journey.osm.example

import java.util.UUID

import com.lapots.game.journey.osm.OSMContext
import com.lapots.game.journey.osm.api.AbstractStatefulObject
import com.lapots.game.journey.osm.domain.{State, Transition}


object Example {

  // represent any object from any system
  class BasicObject {}

  class BasicStateful extends AbstractStatefulObject[BasicObject] {
    override var obj: BasicObject = _

    override def getState(): State = ???

    override def nextState(): Unit = ???

    override def nextState(func: Transition): Unit = ???
  }

  def main(args: Array[String]): Unit = {
    val obj = new BasicObject
    val statefulObject = new BasicStateful()
    statefulObject.obj = obj

    // now we can control the state of object from OMSContext instead of manually
    // ideally I think I can hide this using AnyRef and dynamic object creation
    OSMContext.registerObject(statefulObject, UUID.randomUUID().toString)
  }
}
