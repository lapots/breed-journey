package com.lapots.game.journey.core.platform.resource.storage.redis

import org.springframework.data.redis.core.RedisTemplate

import org.springframework.beans.factory.annotation.Autowired

abstract class RedisSynchronousStorage {

    @Autowired
    RedisTemplate<String, String> template

    abstract def read(key)

    abstract def write(key, value)
}
