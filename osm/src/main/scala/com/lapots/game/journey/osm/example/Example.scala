package com.lapots.game.journey.osm.example

import com.lapots.game.journey.ims.domain.dsl.GRLMessageDSL
import com.lapots.game.journey.osm.OSMPlatform
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
    // want some dynamic solution by it is not possible
    // as I need to implement custom state change mechanism.
    val statefulObject = new BasicStateful()
    statefulObject.obj = obj

    // need some DSL like solution as it does not work that way
    var message = new GRLMessageDSL
    message = message.dsl (
      message.headers(
        message.header = new kotlin.Pair("key", "value")
      )
    )
    // now we can control the state of object from OMSContext instead of manually
    // ideally I think I can hide this using AnyRef and dynamic object creation
    OSMPlatform.registerObject(statefulObject)
  }
}
