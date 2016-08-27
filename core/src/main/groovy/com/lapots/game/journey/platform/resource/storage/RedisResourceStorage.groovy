package com.lapots.game.journey.platform.resource.storage

import com.lapots.game.journey.platform.resource.ResourceConnection;

@Singleton
class RedisResourceStorage {

    def read_storage = [
        "java.lang.String" : { key ->
            ResourceConnection.instance.redis().sync().get(key)
        }
    ]

    def write_storage = [
        "java.lang.String" : { key, value ->
            ResourceConnection.instance.redis().sync().set(key, value)
        }
    ]

    def read(key) { read_storage[key.getClass().name](key) }

    def write(key, value) {
        write_storage[key.getClass().name] (key, value)
    }

}
