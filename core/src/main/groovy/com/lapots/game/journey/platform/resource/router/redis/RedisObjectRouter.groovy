package com.lapots.game.journey.platform.resource.router.redis

import com.lapots.game.journey.platform.resource.storage.redis.RedisSynchronousStringStorage

@Singleton
class RedisObjectRouter {

    def read_channels = [
        "java.lang.String" : { key ->
            RedisSynchronousStringStorage.instance.read(key)
        }
    ]

    def write_channels = [
        "java.lang.String" : { key, value ->
            RedisSynchronousStringStorage.instance.write(key, value)
        }
    ]

    def write(key, value) {
        write_channels[value.getClass().name](key, value)
    }

    def read(key, expected_type) {
        read_channels[expected_type](key)
    }
}
