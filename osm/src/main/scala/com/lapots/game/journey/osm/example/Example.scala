package com.lapots.game.journey.osm.example

import com.lapots.game.journey.osm.OSMPlatform
import com.lapots.game.journey.osm.api.AbstractStatefulObject
import com.lapots.game.journey.osm.domain.{State, Transition}


object Example {

  // represent any object from any system
  class BasicObject {
    var fieldA : String = _
    var fieldB : Int = _
  }

  class BasicStateful extends AbstractStatefulObject[BasicObject] {
    override var obj: BasicObject = _
    override var states: Array[State] = _
    // I want to populate it somehow
    override var transitionFunctions: Array[Transition] = _

    override def getState(): State = ???

    override def nextState(): Unit = ???

    override def nextState(func: Transition): Unit = ???
  }

  class BasicTransition
  def main(args: Array[String]): Unit = {
    val obj = new BasicObject
    // want some dynamic solution by it is not possible
    // as I need to implement custom state change mechanism.
    val statefulObject = new BasicStateful()
    statefulObject.obj = obj

    // now we can control the state of object from OMSContext instead of manually
    // ideally I think I can hide this using AnyRef and dynamic object creation
    // maybe even through [implicit]. Who knows.
    OSMPlatform.registerObject(statefulObject)
  }
}
