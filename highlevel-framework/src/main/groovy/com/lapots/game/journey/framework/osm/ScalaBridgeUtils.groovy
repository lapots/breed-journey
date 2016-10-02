package com.lapots.game.journey.framework.osm

import com.lapots.game.journey.osm.domain.ObjectState

/**
 * Transforming Groovy objects into Scala objects.
 */
class ScalaBridgeUtils {

    // converts Groovy list to Scala list
    static def toScalaList(list) {
        scala.collection.JavaConverters.collectionAsScalaIterable(list).toList()
    }

    // convert Scala map to Groovy
    static def toScalaMap(map) {
        scala.collection.JavaConverters.mapAsScalaMap(map)
    }
}
