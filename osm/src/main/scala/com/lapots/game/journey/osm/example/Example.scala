package com.lapots.game.journey.osm.example

import com.lapots.game.journey.osm.OSMPlatform
import com.lapots.game.journey.osm.api.{IStateful, ITransformation}
import com.lapots.game.journey.osm.domain.{FiniteStatefulObject, ObjectState}

object Example {

  // represent any object from any system
  class BasicObject {
    var fieldA : String = _
    var fieldB : Int = _

    override def toString: String = {
      "Basic object: $fieldA : $fieldB"
    }
  }

  class FieldBTransformation extends ITransformation[Int] {

    override def transform(input: Int, misc: Map[String, String]): Int = {
      input * 10
    }
  }
  class FieldATransformation extends ITransformation[String] {

    override def transform(input: String, misc: Map[String, String]): String = {
      // ignoring misc for now
      input + "_transformed"
    }
  }

  def main(args: Array[String]): Unit = {
    val obj = new BasicObject
    obj.fieldA = "field"
    obj.fieldB = 10
    // OSM basic flow for FS object
    // 1. create instance of wrapper
    val osmObject = new FiniteStatefulObject[BasicObject]
    osmObject.init(obj, List("fieldA", "fieldB"))

    // 2. create states
    val state1 = new ObjectState
    state1.objectParameters += ("fieldA" -> "state1field")
    state1.objectParameters += ("fieldB" -> 14)
    val state2 = new ObjectState
    state2.objectParameters += ("fieldA" -> "state2field")
    state2.objectParameters += ("fieldB" -> 20)

    // 3. register states
    osmObject.registerState(state1)
    osmObject.registerState(state2)

    // 4. register object in OSM
    val objId = OSMPlatform.registerObject(osmObject)
    // 5. read, update object
    // current state
    println(osmObject.currentState)
    println(obj)
    // switch state -> basically we can do it through the object
    // but it is better to use OSMPlatform for that
    OSMPlatform.nextState(objId)
    println(osmObject.currentState)
  }
}
