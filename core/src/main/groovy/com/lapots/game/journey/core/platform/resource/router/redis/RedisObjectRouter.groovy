package com.lapots.game.journey.core.platform.resource.router.redis

import com.lapots.game.journey.core.platform.resource.storage.redis.RedisSynchronousStringStorage
import org.springframework.stereotype.Component

import org.springframework.beans.factory.annotation.Autowired

@Component
class RedisObjectRouter {

    @Autowired
    RedisSynchronousStringStorage redisStorage

    def read_channels = [
        "java.lang.String" : { key ->
            redisStorage.read(key)
        }
    ]

    def write_channels = [
        "java.lang.String" : { key, value ->
            redisStorage.write(key, value)
        }
    ]

    def write(key, value) {
        write_channels[value.getClass().name](key, value)
    }

    def read(key, expected_type) {
        read_channels[expected_type](key)
    }
}
