package com.lapots.game.journey.osm.example

import com.lapots.game.journey.osm
import com.lapots.game.journey.osm.api.ITransformation
import com.lapots.game.journey.osm.domain.Transition
import com.lapots.game.journey.osm.{OSMContext, OSMPlatform}

object Example {

  // represent any object from any system
  class BasicObject {
    var fieldA : String = _
    var fieldB : Int = _

    override def toString: String = {
      "Basic object: $fieldA : $fieldB"
    }
  }

  def main(args: Array[String]): Unit = {
    val obj = new BasicObject
    obj.fieldA = "field"
    obj.fieldB = 10

    // 1. register object in OSM and get id - might be useful
    val id = OSMPlatform.registerObject(obj, List("fieldA", "fieldB"))

    // 2. create transitions
    val transition = new Transition

    // 3. create field transformations and register it for transition
    val transformationFieldA = new ITransformation {
      override def transform(input: Any, misc: Map[String, String]): Any = {
        var stringInstance = input.asInstanceOf[String]
        stringInstance += "_transformed"
        stringInstance
      }
    }
    transition.registerTransformation("fieldA", transformationFieldA)

    // 4. acquire object from context
    val objState = OSMPlatform.retrieveObject(id)
    println (objState.toString)

    // 5. apply transformation
    transition.Apply.applyTransformations(transition, objState)
    println(objState.toString)

    println (s"Original object fields: ${obj.fieldA}")
  }
}
