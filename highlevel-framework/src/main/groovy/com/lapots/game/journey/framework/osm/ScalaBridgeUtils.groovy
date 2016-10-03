package com.lapots.game.journey.framework.osm

import scala.collection.*
import scala.*

/**
 * Transforming Groovy objects into Scala objects.
 */
class ScalaBridgeUtils {

    // converts Groovy list to Scala list
    static def toScalaList(list) {
        JavaConverters.collectionAsScalaIterable(list).toList()
    }

    // convert Scala map to Groovy
    // immutable map BTW
    static def toScalaMap(map) {
        JavaConverters.mapAsScalaMapConverter(map).asScala().toMap(Predef.conforms())
    }
}
